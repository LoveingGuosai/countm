package com.guosai.countMoney;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by qiyang on 2015/8/21.
 */
public class SixYearBefore {
    private List<Invoice> invoices = new ArrayList<>();

    public void IBeginLoveYou(String you, String me) {
        XSSFWorkbook workbook_mon;
        XSSFWorkbook workbook_all;
        try {
            workbook_mon = new XSSFWorkbook(you);
            workbook_all = new XSSFWorkbook(me);
            parseSheet(workbook_mon.getSheetAt(0));
            beginadd(workbook_all.getSheetAt(1));
            File home_dir = FileSystemView.getFileSystemView().getHomeDirectory();
            String path = home_dir.getAbsolutePath();
            System.out.println("生成汇合Excel" + path + File.separator+"Desktop"+File.separator + "汇总.xlsx");
            File file = new File( path + File.separator+"Desktop"+File.separator + "汇总.xlsx");
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook_all.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void beginadd(XSSFSheet sheet) {
        int count = 1;
        sheet.setForceFormulaRecalculation(true);
        int rowcount = sheet.getLastRowNum();
        for (Invoice invoice : invoices) {
            boolean done = false;
            for (int i = 1; i < sheet.getLastRowNum() + 1 && !done; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().contains(invoice.getDev_name())) {
                    done = true;
                }
            }
            if (!done) {
                int rownum = rowcount + count++;
                XSSFRow xssfrow = sheet.createRow(rownum);
                xssfrow.createCell(0).setCellValue(invoice.getDev_name());
                for (int i = 1; i <getweikai(sheet.getRow(0)) ; i++) {
                    XSSFCell xssfCell = xssfrow.createCell(i);
                    xssfCell.setCellType(Cell.CELL_TYPE_FORMULA);
                    xssfrow.createCell(i).setCellFormula("0");
                }
                XSSFCell xssfCell = xssfrow.createCell(getweikai(sheet.getRow(0)));
                xssfCell.setCellType(Cell.CELL_TYPE_FORMULA);
                xssfCell.setCellFormula("B" + (rownum + 1) + "-C" +(rownum+1));
            }
        }
        for (Invoice invoice : invoices) {
            XSSFRow title = sheet.getRow(0);
            String last = title.getCell(getweikai(title)-1).getStringCellValue();
            int last_month = Integer.valueOf(last.substring(0, last.indexOf("月")));
            if ((last_month == 12 && invoice.getMonth() == 1) || invoice.getMonth() > last_month) {
                addCol(sheet, invoice.getMonth());
            }
        }
        XSSFRow title = sheet.getRow(0);
        System.out.println(invoices.size());
        int y=0;
        for (Invoice invoice : invoices) {
            boolean flag = false;
            for (int i = 1; i < sheet.getLastRowNum() + 1 && !flag; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().contains(invoice.getDev_name())) {
                    comparerow(invoice, row, i + 1, title);
                    y++;
                    flag = true;
                }else {
                   // System.out.println(invoice.getDev_name()+"--"+invoice.getInvoice_name());
                }
            }
        }
        System.out.println(y+"yy");
    }

    private void comparerow(Invoice invoice, XSSFRow row, int i, XSSFRow title) {
        try {
            if(row.getCell(2)==null)
                row.createCell(2);
            row.getCell(2).setCellFormula("SUM(D" + i + ":" + getExcelColName(title.getLastCellNum() - 1) + i + ")");
        }catch (Exception e){
            System.out.println(1111);
        }
        int month = invoice.getMonth();
        int number = 0;
        for (int y = getweikai(title)-1; y > 4; y--) {
            String content = title.getCell(y).getStringCellValue();
           // System.out.println(content);
            if (month == Integer.valueOf(content.substring(0, content.indexOf("月")))) {
                number = y;
                break;
            }
        }
        if (number != 0) {
            XSSFCell cell = row.getCell(number);
            if(cell==null){
                cell=row.createCell(number);
                cell.setCellType(Cell.CELL_TYPE_FORMULA);
                cell.setCellFormula("0");
            }
            if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                cell.setCellFormula(cell.getCellFormula() + invoice.getMoney());
            } else {
                String old;
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    old = String.valueOf(cell.getNumericCellValue());
                } else {
                    old = cell.getStringCellValue();
                }
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellFormula(old + invoice.getMoney());
            }
        }
    }

    private String getExcelColName(int n) {
        String s = "";
        while (n > 0) {
            int m = n % 26;
            if (m == 0) m = 26;
            s = (char) (m + 64) + s;
            n = (n - m) / 26;
        }
        return s;
    }

    private void addCol(XSSFSheet sheet, int month) {
        XSSFRow title = sheet.getRow(0);
        XSSFCell lastcell = title.getCell(getweikai(title));
        XSSFCell newcell = title.createCell(getweikai(title) + 1, Cell.CELL_TYPE_STRING);
        newcell.setCellValue(lastcell.getStringCellValue());
        lastcell.setCellValue(month + "月开票汇总");
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell last = row.getCell(getweikai(title)-1);
            XSSFCell new_cell = row.createCell(getweikai(title), Cell.CELL_TYPE_FORMULA);
            new_cell.setCellFormula("B" + (i + 1) + "-" + "C" + (i + 1));
            last.setCellType(Cell.CELL_TYPE_FORMULA);
            last.setCellFormula("0");
        }
    }


    private void parseSheet(XSSFSheet sheet) {
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            try {
                    //System.out.println(i);
                Invoice invoice = Optional.ofNullable(Invoice.parseFromRow(sheet.getRow(i))).get();
                try {
                    Invoice oldi = invoices.stream().filter(invoice1 -> invoice1.equals(invoice)).findFirst().get();
                    int index = invoices.indexOf(oldi);
                    oldi.setMoney(oldi.getMoney() + invoice.getMoney());
                    invoices.set(index, oldi);
                } catch (NoSuchElementException e1) {
                    invoices.add(invoice);
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
    }
    private int getweikai(XSSFRow title){
        int number = 0;
        for (int i = 0; i < title.getLastCellNum() + 1; i++) {
            if(title.getCell(i).getStringCellValue().contains("未开汇总")){
                number=i;
                break;
            }
        }
        return number;
    }

}
