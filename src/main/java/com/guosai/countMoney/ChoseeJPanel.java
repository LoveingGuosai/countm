package com.guosai.countMoney;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by qiyang on 2015/8/17.
 */
public class ChoseeJPanel extends JPanel {
    private Image image;
    private int i;

    public ChoseeJPanel(int i) {
        super();
        this.i=i;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        URL url = ChoseeJPanel.class.getResource("/images/" + i + ".jpg");
        ImageIcon imageIcon = new ImageIcon(url);
        g.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
      //  imageIcon.setImage(imageIcon.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST));
        //imageIcon.paintIcon(this,g,0,0);
    }
}
