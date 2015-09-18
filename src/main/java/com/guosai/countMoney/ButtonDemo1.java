package com.guosai.countMoney;

import javax.swing.*;

/**
 * Created by qiyang on 15-8-19.
 */
public class ButtonDemo1 {
    public static void main(String[] args){
        JFrame jFrame = new ChoseeJFrame();
        jFrame.add(new ChoseeJPanel(1));
        jFrame.setVisible(true);
    }
}
