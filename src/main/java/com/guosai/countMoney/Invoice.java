package com.guosai.countMoney;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * Created by qiyang on 2015/8/21.
 */
public class Invoice {
    private int month;
    private String dev_name;
    private String money;
    private String invoice_name;

    private boolean equalname;

    public Invoice() {
    }

    public static Invoice parseFromRow(XSSFRow row) {
        try {
            Invoice invoice = new Invoice();
            String formatTime = getStringValue(row.getCell(1)).replace(".", "").replace("月", "").replace("日", "");
            invoice.setMonth(Integer.valueOf(formatTime.substring(0, formatTime.length() - 2)));
            invoice.setDev_name(row.getCell(7).getStringCellValue());
            invoice.setMoney(getCellStringValue(row.getCell(4)));
            invoice.setInvoice_name(row.getCell(3).getStringCellValue());
            if (!invoice.isEqualname()) {
                FileUtil.writeDiffNameLog(invoice.getDev_name() + "----->(发票抬头)" + invoice.getInvoice_name() + "金额" + invoice.getMoney());
                return null;
            }
            return invoice;
        }catch (Exception e){
            System.out.println("发票表格第"+row.getRowNum()+"行出错，请检查是否有空的单元格或者空行");
            return null;
        }
    }

    private static String getStringValue(XSSFCell xssfCell) {
        double content = xssfCell.getNumericCellValue();
        String str = String.valueOf(content);
        String suffix = str.substring(str.indexOf(".")+1);
        if (suffix.length() == 1) {
            str += "0";
        }
        return str;

    }

    private static String getCellStringValue(XSSFCell xssfCell) {
        //System.out.println(xssfCell.getCellType()+"type");
        if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return "+" + String.valueOf(xssfCell.getNumericCellValue());
        } else if(xssfCell.getCellType()==Cell.CELL_TYPE_FORMULA){
            return "+"+xssfCell.getCellFormula();
        }else {
            return "+" + xssfCell.getStringCellValue();
        }
    }

    public boolean isEqualname() {
        return dev_name.equals(invoice_name);
    }

    public void setEqualname(boolean equalname) {
        this.equalname = equalname;
    }

    public String getInvoice_name() {
        return invoice_name;
    }

    public void setInvoice_name(String invoice_name) {
        this.invoice_name = invoice_name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;
        return month == ((Invoice) o).getMonth() && dev_name.equals(((Invoice) o).getDev_name());

    }

    @Override
    public int hashCode() {
        int result = month;
        result = 31 * result + (dev_name != null ? dev_name.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (invoice_name != null ? invoice_name.hashCode() : 0);
        result = 31 * result + (equalname ? 1 : 0);
        return result;
    }
}
