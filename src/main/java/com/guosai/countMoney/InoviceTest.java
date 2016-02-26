package com.guosai.countMoney;

/**
 * Created by qiyang on 15-8-24.
 */
public class InoviceTest {
    public static void main(String[] args){
        String file1="/home/user/文档/ccopms-rechargeList-2015-10-15.xlsx";
        String file2="/home/user/文档/10.13充值详单新.xlsx";
        SixYearBefore sixYearBefore = new SixYearBefore();
        sixYearBefore.IBeginLoveYou(file1,file2);
    }
}
