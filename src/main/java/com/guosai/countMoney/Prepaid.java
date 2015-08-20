package com.guosai.countMoney;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.Date;

/**
 * Created by qiyang on 2015/8/18.
 */
public class Prepaid {
    private String dev_name;
    private String email;
    private double pay;
    private String payWay;
    private String payDate;
    private String role;

    public static Prepaid parseFromRow(XSSFRow xssfRow,String date){
        if(xssfRow.getCell(5).getStringCellValue().equals("系统")){
            return null;
        }
        Prepaid prepaid = new Prepaid();
        if(date.equals(xssfRow.getCell(0).getStringCellValue())){
            prepaid.setPayDate(xssfRow.getCell(0).getStringCellValue());
            prepaid.setRole(xssfRow.getCell(1).getStringCellValue());
            prepaid.setDev_name(xssfRow.getCell(2).getStringCellValue());
            prepaid.setEmail(xssfRow.getCell(3).getStringCellValue());
            prepaid.setPay(Double.valueOf(xssfRow.getCell(4).getStringCellValue()));
            prepaid.setPayWay(xssfRow.getCell(5).getStringCellValue());
            if(prepaid.getDev_name()==null||prepaid.getDev_name().equals("")){
                return null;
            }
            return prepaid;
        }

        return null;
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

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
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
