package com.guosai.countMoney;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * Created by qiyang on 15-8-17.
 */
public class ChoseeJFrame extends JFrame{
    private Image image;

    public  ChoseeJFrame(){
        this.setTitle("转换Excel for guosai");
        this.setResizable(false);
        this.setSize(550, 800);
        URL url = ChoseeJFrame.class.getResource("/images/background.jpg");
        this.setIconImage(new ImageIcon(url).getImage());
    }

    public JComponent initFileChooser(){
        JComponent component = new JFileChooser();
        return component;
    }
}
