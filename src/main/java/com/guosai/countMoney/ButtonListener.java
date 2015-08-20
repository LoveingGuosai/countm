package com.guosai.countMoney;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;

/**
 * Created by qiyang on 15-8-19.
 */
public class ButtonListener implements ActionListener {
    private JFrame jFrame;
    private File file1;
    private File file2;
    private JFileChooser jFileChooser;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField textField;
    private JLabel jLabel;

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }

    public ButtonListener() {
        jFrame = new ChoseeJFrame();
        Container container = jFrame.getContentPane();
        textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 300, 550, 500);
        scrollPane.setViewportView(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textField = new JTextField();
        textField.setName("请输入选择日期");
        textField.setBounds(300, 50, 100, 30);
        jLabel = new JLabel();
        jLabel.setText("输入需要转化的日期（1992-06-10）");
        jLabel.setBounds(20, 50, 300, 30);
        jLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        jLabel.setForeground(Color.black);
        JPanel jPanel = new ChoseeJPanel();
        jPanel.setBackground(Color.pink);
        jPanel.setLayout(null);
        JButton jButton1 = new JButton("选择今日Excel");
        jButton1.setActionCommand("chose_today");
        jButton1.setBounds(100, 10, 100, 30);
        jButton1.addActionListener(this);
        JButton jButton2 = new JButton("选择汇总Excel");
        jButton2.setActionCommand("chose_all");
        jButton2.setBounds(300, 10, 100, 30);
        jButton2.addActionListener(this);
        JButton jButton3 = new JButton("开始");
        jButton3.setActionCommand("begin");
        jButton3.setBounds(200, 100, 100, 30);
        jButton3.addActionListener(this);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(textField);
        jPanel.add(scrollPane);
        jPanel.add(jLabel);
        container.add(jPanel);
        jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setApproveButtonText("确定");
        PrintStream printStream = new PrintStream(new AreOutPutStream(textArea));
        //System.setOut(printStream);
        //System.setErr(printStream);
        // jFrame.pack();
        jFrame.setVisible(true);

    }

    public static void main(String[] args) {
        ButtonListener buttonListener = new ButtonListener();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result;
        if (e.getActionCommand().equals("chose_today")) {
            jFileChooser.setDialogTitle("打开当日excel");
            result = jFileChooser.showOpenDialog(jFrame);
            if (result == jFileChooser.APPROVE_OPTION) {
                file1 = jFileChooser.getSelectedFile();
                System.out.println("小笨猪,你选择单日Excel为"+file1.getAbsolutePath());
            } else {

            }
        } else if (e.getActionCommand().equals("chose_all")) {
            jFileChooser.setDialogTitle("打开汇总excel");
            result = jFileChooser.showOpenDialog(jFrame);
            if (result == jFileChooser.APPROVE_OPTION) {
                file2 = jFileChooser.getSelectedFile();
                System.out.println("小笨猪,你选择汇总Excel为"+file2.getName());
            } else {

            }
        } else  if(e.getActionCommand().equals("begin")){
            LovingAction lovingAction = new LovingAction();
            System.out.println(file1.getAbsolutePath());
            System.out.println(file2.getName());
            String date= textField.getText();
            System.out.println("您选择要合并的日期是："+date);
            System.out.println("请稍等---------您的大脑正在运算中。。。。。。。。。。BIBIBIBI");
            lovingAction.action(file1.getAbsolutePath(),file2.getAbsolutePath(),date);

        }
    }
}

