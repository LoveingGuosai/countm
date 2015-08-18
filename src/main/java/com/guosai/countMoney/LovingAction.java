package com.guosai.countMoney;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * Created by qiyang on 15-8-18.
 */
public class LovingAction {
    public void action(String file1, String file2) {
        XSSFWorkbook xssfWorkbook_mon;
        XSSFWorkbook xssfWorkbook_all;
        XSSFSheet xssfSheet_mon;
        XSSFSheet xssfSheet_all;

        try {
            xssfWorkbook_mon = new XSSFWorkbook(file1);
            xssfWorkbook_all = new XSSFWorkbook(file2);
            xssfSheet_mon = xssfWorkbook_mon.getSheet("");
            xssfSheet_all = xssfWorkbook_all.getSheet("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}