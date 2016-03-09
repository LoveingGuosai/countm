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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyang on 2016/3/8.
 */
public class NeedInvoicAction {
    private List<Invoiced> invoiceds = new ArrayList<>();
    public void action(String all,String invoiced){
        XSSFWorkbook workbook_invoiced ;
        XSSFWorkbook workbook_all;
        try {
            workbook_invoiced=new XSSFWorkbook(invoiced);
            workbook_all = new XSSFWorkbook(all);
            XSSFSheet invoiced_sheet = workbook_invoiced.getSheetAt(0);
            XSSFSheet workbook_allSheetAt = workbook_all.getSheetAt(1);
            for (int i =1;i<invoiced_sheet.getLastRowNum()+1;i++){
                XSSFRow row=invoiced_sheet.getRow(i);
              if(row.getCell(3)!=null){
                  parseInvoiced(row);
              }
            }
            int last_index = getweikai(workbook_allSheetAt.getRow(0));
            for(Invoiced invoiced1:invoiceds){
//                boolean flag = false;
                for(int j=1;j<workbook_allSheetAt.getLastRowNum()+1;j++){
                    XSSFRow row = workbook_allSheetAt.getRow(j);
                    if(row.getCell(0).getStringCellValue().contains(invoiced1.getDevName())){
//                        flag = true;
                        invoiced1.setNeedInvoice(Double.valueOf(row.getCell(last_index).getNumericCellValue()).toString());
                        break;
                    }
                }
            }
            writeBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeBook() throws IOException {
        XSSFWorkbook result_book = new XSSFWorkbook();
        String path = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        File file = new File( path + File.separator+"Desktop"+File.separator+LocalDate.now().toString() + "汇总.xlsx");
//        File file = new File( path + File.separator+ LocalDate.now().toString() + "汇总.xlsx");
        XSSFSheet result_bookSheet = result_book.createSheet("需要开票");
        XSSFRow row = result_bookSheet.createRow(0);
        XSSFCell name = row.createCell(0);
        name.setCellType(Cell.CELL_TYPE_STRING);
        name.setCellValue("名称");
        XSSFCell invoiced = row.createCell(1);
        invoiced.setCellType(Cell.CELL_TYPE_STRING);
        invoiced.setCellValue("金额");
        XSSFCell needInvoiced = row.createCell(2);
        needInvoiced.setCellType(Cell.CELL_TYPE_STRING);
        needInvoiced.setCellValue("未开");
        int i = 1;
        for (Invoiced invoiced1 : invoiceds) {
            XSSFRow xssfRow = result_bookSheet.createRow(i++);
            XSSFCell cell_name = xssfRow.createCell(0);
            cell_name.setCellType(Cell.CELL_TYPE_STRING);
            cell_name.setCellValue(invoiced1.getDevName());
            XSSFCell cell_invoiced = xssfRow.createCell(1);
            cell_invoiced.setCellType(Cell.CELL_TYPE_STRING);
            cell_invoiced.setCellValue(invoiced1.getMoney());
            XSSFCell cell_needInvoice= xssfRow.createCell(2);
            cell_needInvoice.setCellType(Cell.CELL_TYPE_STRING);
            cell_needInvoice.setCellValue(invoiced1.getNeedInvoice());
        }
        if(file.exists())
            file.delete();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        result_book.write(fileOutputStream);
        fileOutputStream.close();

        System.out.println(path + File.separator+ LocalDate.now().toString() + "汇总.xlsx");
    }
    public void parseInvoiced(XSSFRow row){
        invoiceds.add(Invoiced.parseFrom(row));
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
