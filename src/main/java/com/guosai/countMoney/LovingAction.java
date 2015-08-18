package com.guosai.countMoney;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by qiyang on 15-8-18.
 */
public class LovingAction {
    private List<Prepaid> prepaids = new ArrayList<>(10);
    private String date;

    public LovingAction() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date=simpleDateFormat.format(new Date());
    }

    public void action(String file1, String file2) {
        XSSFWorkbook xssfWorkbook_mon;
        XSSFWorkbook xssfWorkbook_all;
        XSSFSheet xssfSheet_mon;
        XSSFSheet xssfSheet_all;

        try {
            xssfWorkbook_mon = new XSSFWorkbook(file1);
            xssfWorkbook_all = new XSSFWorkbook(file2);
            System.out.println(xssfWorkbook_mon);
            System.out.println(xssfWorkbook_all.getSheetName(1));
            xssfSheet_mon = xssfWorkbook_mon.getSheetAt(0);
            System.out.println(xssfSheet_mon.getSheetName());
            xssfSheet_all = xssfWorkbook_all.getSheetAt(1);
            parseSheet(xssfSheet_mon);
            beginAdd(xssfSheet_all);
            File home_dir = FileSystemView.getFileSystemView().getHomeDirectory();
            String path = home_dir.getAbsolutePath();
            System.out.println(path+File.separator+"Лузм.xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(path+File.separator+"Лузм.xlsx");
            xssfWorkbook_all.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parseSheet(XSSFSheet xssfSheet){

        for (int i =1;i<xssfSheet.getLastRowNum();i++){
            try {
                prepaids.add(Optional.ofNullable(Prepaid.parseFromRow(xssfSheet.getRow(i),date)).get());
            }catch (NoSuchElementException e){
                continue;
            }
        }
    }
    private void beginAdd(XSSFSheet xssfSheet){
        int count=1;
        int rowcount=xssfSheet.getLastRowNum();
        for(Prepaid prepaid:prepaids){
            boolean done = false;
            for(int i=1;i<rowcount&&!done;i++){
                System.out.println(xssfSheet.getRow(i).getCell(0).getStringCellValue()+"-->"+i);
                if(prepaid.getDev_name().equals(xssfSheet.getRow(i).getCell(0).getStringCellValue())){
                    double money = xssfSheet.getRow(i).getCell(1).getNumericCellValue();
                    xssfSheet.getRow(i).getCell(1).setCellValue(money+prepaid.getPay());
                    done=true;
                }
            }
            if(!done){
                XSSFRow xssfrow = xssfSheet.createRow(rowcount+count++);
                xssfrow.createCell(0).setCellValue(prepaid.getDev_name());
                xssfrow.createCell(1).setCellValue(prepaid.getPay());
            }
        }
    }

}