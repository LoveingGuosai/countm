package com.guosai.countMoney;

import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * Created by qiyang on 2016/3/8.
 */
public class Invoiced {
    private String devName;
    private String money;
    private String needInvoice;

    public String getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(String needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public static Invoiced parseFrom(XSSFRow row) {
        Invoiced invoiced  = new Invoiced();
        invoiced.setDevName(row.getCell(3).getStringCellValue());
        invoiced.setMoney(Double.valueOf(row.getCell(4).getNumericCellValue()).toString());
        invoiced.setNeedInvoice("");
        return invoiced;
    }
}
