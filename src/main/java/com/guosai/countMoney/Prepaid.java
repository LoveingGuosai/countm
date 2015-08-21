package com.guosai.countMoney;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.Date;

/**
 * Created by qiyang on 2015/8/18.
 */
public class Prepaid {
    private String dev_name;
    private String email;
    private String pay;
    private String payWay;
    private String payDate;
    private String role;

    public static Prepaid parseFromRow(XSSFRow xssfRow, String date) {
        try {
            if (date.equals(getCellStringValue(xssfRow.getCell(0)))) {
                if (xssfRow.getCell(5).getStringCellValue().equals("系统")) {
                    return null;
                }
                if (getCellStringValue(xssfRow.getCell(1)).trim().equals("开发者") ||
                        xssfRow.getCell(1).getStringCellValue().trim().equals("个人开发者")) {
                    return null;
                }
                if (getCellStringValue(xssfRow.getCell(2)) == null || getCellStringValue(xssfRow.getCell(2)).equals(""))
                    return null;
                Prepaid prepaid = new Prepaid();
                prepaid.setPayDate(getCellStringValue(xssfRow.getCell(0)));
                prepaid.setRole(getCellStringValue(xssfRow.getCell(1)));
                prepaid.setDev_name(getCellStringValue(xssfRow.getCell(2)));
                prepaid.setEmail(getCellStringValue(xssfRow.getCell(3)));
                prepaid.setPay(formatFormalFromString(getCellStringValue(xssfRow.getCell(4))));
                prepaid.setPayWay(getCellStringValue(xssfRow.getCell(5)));
                return prepaid;
            }

            return null;
        }catch (Exception e){
            System.out.println(xssfRow.getRowNum());
            return null;
        }
    }
    private static String getCellStringValue(XSSFCell xssfCell){
        if(xssfCell.getCellType()== Cell.CELL_TYPE_NUMERIC){
            return String.valueOf(xssfCell.getNumericCellValue());
        }else {
            return xssfCell.getStringCellValue();
        }
    }
    private static String formatFormalFromString(String pay){
        double d = Double.valueOf(pay);
        if(d>=0){
            return "+"+pay;
        }
        return pay;
    }

    public Prepaid() {
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Prepaid{" +
                "dev_name='" + dev_name + '\'' +
                ", email='" + email + '\'' +
                ", pay=" + pay +
                ", payWay=" + payWay +
                ", payDate=" + payDate +
                ", role='" + role + '\'' +
                '}';
    }
}
