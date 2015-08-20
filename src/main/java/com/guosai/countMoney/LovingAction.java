package com.guosai.countMoney;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by qiyang on 15-8-18.
 */
public class LovingAction {
    private String date;

    public void action(String file1, String file2, String date) {
        this.date = date;
        XSSFWorkbook xssfWorkbook_mon;
        XSSFWorkbook xssfWorkbook_all;
        XSSFSheet xssfSheet_mon;
        XSSFSheet xssfSheet_all;

        try {
            xssfWorkbook_mon = new XSSFWorkbook(file1);
            xssfWorkbook_all = new XSSFWorkbook(file2);
            System.out.println("读取Excel到内存中-----");
            xssfSheet_mon = xssfWorkbook_mon.getSheetAt(0);
            System.out.println("获取单日Excel sheet1");
            xssfSheet_all = xssfWorkbook_all.getSheetAt(1);
            System.out.println("获取汇总Excel sheet2");
            List<Prepaid> prepaids = new ArrayList<>();
            parseSheet(xssfSheet_mon, prepaids);
            beginAdd(xssfSheet_all, prepaids);
            File home_dir = FileSystemView.getFileSystemView().getHomeDirectory();
            String path = home_dir.getAbsolutePath();
            System.out.println("生成汇合Excel" + path + File.separator+"desktop"+File.separator + "汇总.xlsx");
            File file = new File(path + File.separator +"desktop"+File.separator+ "汇总.xlsx");
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator + "汇总.xlsx");
            xssfWorkbook_all.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseSheet(XSSFSheet xssfSheet, List<Prepaid> prepaids) {

        for (int i = 1; i < xssfSheet.getLastRowNum(); i++) {
            try {
                boolean flag = false;
                Prepaid prepaid = Optional.ofNullable(Prepaid.parseFromRow(xssfSheet.getRow(i), date)).get();
                for (Prepaid p : prepaids) {
                    if (p.getDev_name().equals(prepaid.getDev_name())) {
                        p.setPay(p.getPay() + prepaid.getPay());
                        flag = true;
                    }
                }
                if (!flag) {
                    prepaids.add(prepaid);
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        if (prepaids.size() == 0) {
            throw new IllegalArgumentException("小笨猪，快检查你选择日期，或者单日文件是否正确");
        }
    }

    private void beginAdd(XSSFSheet xssfSheet, List<Prepaid> prepaids) {
        int count = 1;
        xssfSheet.setForceFormulaRecalculation(true);
        int rowcount = xssfSheet.getLastRowNum();
        for (Prepaid prepaid : prepaids) {
            boolean done = false;
            for (int i = 1; i < rowcount && !done; i++) {
                // System.out.println(xssfSheet.getRow(i).getCell(0).getStringCellValue()+"-->"+i);
                if (prepaid.getDev_name().equals(xssfSheet.getRow(i).getCell(0).getStringCellValue())) {
                    double money = xssfSheet.getRow(i).getCell(1).getNumericCellValue();
                    XSSFCell xssfCell = xssfSheet.getRow(i).getCell(1);
                    //TODO 判断是哪种类型的单元格
                    if(xssfCell.getCellType()== Cell.CELL_TYPE_FORMULA) {
                        if (prepaid.getPay() > 0)
                            xssfCell.setCellFormula(xssfCell.getCellFormula() + "+" + prepaid.getPay());
                        else
                            xssfCell.setCellFormula(xssfCell.getCellFormula() + "-" + prepaid.getPay());
                    }else {

                    }
                    done = true;
                }
            }
            if (!done) {
                XSSFRow xssfrow = xssfSheet.createRow(rowcount + count++);
                xssfrow.createCell(0).setCellValue(prepaid.getDev_name());
                xssfrow.createCell(1).setCellValue(prepaid.getPay());
            }
        }
    }

}