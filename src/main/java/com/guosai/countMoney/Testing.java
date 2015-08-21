package com.guosai.countMoney;

/**
 * Created by qiyang on 15-8-21.
 */
public class Testing {
    public static void main(String[] args){
        String file1="/home/user/下载/8.20充值表.xlsx";
        String file2="/home/user/下载/8.21备份.xlsx";
        LovingAction lovingAction = new LovingAction();
        lovingAction.action(file1,file2,"2015-08-20");
    }
}
