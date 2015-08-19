package com.guosai.countMoney;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by qiyang on 2015/8/17.
 */
public class ChoseeJPanel extends JPanel {
    private Image image;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        URL url = ChoseeJPanel.class.getResource("/images/background.jpg");
      ImageIcon imageIcon = new ImageIcon(url);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST));
        imageIcon.paintIcon(this,g,0,0);
    }
}
