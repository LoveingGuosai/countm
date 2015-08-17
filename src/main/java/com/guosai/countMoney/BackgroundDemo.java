package com.guosai.countMoney;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by qiyang on 2015/8/17.
 */
public class BackgroundDemo extends JFrame{
private Image image;
private static final long serialVersionUID=-1588458291133087637L;
public BackgroundDemo(){
this.setTitle("郭赛");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0 , 0, 400,300 );
        JPanel contentPane=new JPanel(){
            @Override
            public void paint(Graphics g) {
                URL url = BackgroundDemo.class.getResource("/background.jpg");
                ImageIcon icon=new ImageIcon(url);
                image=icon.getImage();
                g.drawImage(image, 0,0,null);
                // TODO Auto-generated method stub
                }
            };
        this.add(contentPane);
        this.setVisible(true);
        }
//    public static void main(String[]args){
//        BackgroundDemo example=new BackgroundDemo();
//        }
    }