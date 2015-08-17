package com.guosai.countMoney;

import javax.swing.*;
import java.awt.*;

/**
 * Created by qiyang on 15-8-17.
 */
public class ChoseeJFrame {
    public void init(){
        JFrame jFrame = new JFrame("选择源Excel");
        Label label = new Label("xxx");
        label.setBackground(Color.PINK);
        jFrame.getContentPane().add(label,BorderLayout.CENTER);
        jFrame.setBackground(Color.PINK);
        jFrame.setSize(400, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    public JComponent initFileChooser(){
        JComponent component = new JFileChooser();
        return component;
    }
}
