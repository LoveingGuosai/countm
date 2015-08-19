package com.guosai.countMoney;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Created by qiyang on 2015/8/17.
 */
public class ChoseeJPanel extends JPanel {
    private Image image;
    public ChoseeJPanel(){
        this.setSize(400,500);
        this.setBorder(new EmptyBorder(5,5,5,5));
    }
    @Override
    public void paint(Graphics g) {
        URL url = ChoseeJPanel.class.getResource("/images/background.jpg");
        ImageIcon imageIcon = new ImageIcon(url);
        image=imageIcon.getImage();
        g.drawImage(image,0,0,null);
    }
}
