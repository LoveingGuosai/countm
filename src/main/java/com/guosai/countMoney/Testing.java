package com.guosai.countMoney;

import java.util.Optional;

/**
 * Created by qiyang on 15-8-21.
 */
public class Testing {
    public static void main(String[] args){
//        String file1="/home/user/下载/8.20充值表.xlsx";
//        String file2="/home/user/下载/8.21备份.xlsx";
//        LovingAction lovingAction = new LovingAction();
//        lovingAction.action(file1,file2,"2015-08-20");
//        Optional optional = Optional.ofNullable(null);
        Testing testing = new Testing();
        System.out.println(testing.getExcelColName(53));
    }
    String getExcelColName(int n)
    {
        String s = "";
        while (n > 0){
            int m = n % 26;
            if (m == 0) m = 26;
            s = (char)(m + 64) + s;
            n = (n - m) / 26;
        }
        return s;
    }
}
