package com.guosai.countMoney;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by qiyang on 15-8-17.
 */
public class ChoseeJFrame extends JFrame{
    private Image image;

    public  ChoseeJFrame(){
        this.setTitle("parse Excel for guosai");
        this.setResizable(false);
        this.setSize(550, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        URL url = ChoseeJFrame.class.getResource("/images/1.jpg");
        this.setIconImage(new ImageIcon(url).getImage());
    }

    public JComponent initFileChooser(){
        JComponent component = new JFileChooser();
        return component;
    }
}