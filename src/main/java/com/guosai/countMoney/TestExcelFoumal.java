package com.guosai.countMoney;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * Created by qiyang on 2015/8/20.
 */
public class TestExcelFoumal {
    public static  void main(String[] args) throws IOException {
        for (int i = 0; i <100 ; i++) {
            new Random(0,2).nextInt();
            System.out.println(new Random(2).nextInt());
        }
    }
}
