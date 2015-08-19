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
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 300, 550, 500);
        scrollPane.setViewportView(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        JPanel jPanel = new ChoseeJPanel();
        jPanel.setBackground(Color.pink);
        jPanel.setLayout(null);
        JButton jButton1 = new JButton("当日文件");
        jButton1.setActionCommand("chose_today");
        jButton1.setBounds(100, 10, 100, 30);
        jButton1.addActionListener(this);
        JButton jButton2 = new JButton("汇总文件");
        jButton2.setActionCommand("chose_all");
        jButton2.setBounds(300, 10, 100, 30);
        jButton2.addActionListener(this);
        JButton jButton3 = new JButton("开始");
        jButton3.setActionCommand("begin");
        jButton3.setBounds(200, 50, 100, 30);
        jButton3.addActionListener(this);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(scrollPane);
        container.add(jPanel);
        jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setApproveButtonText("确定");
        PrintStream printStream = new PrintStream(new AreOutPutStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
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
            } else {

            }
        } else if (e.getActionCommand().equals("chose_all")) {
            jFileChooser.setDialogTitle("打开汇总excel");
            result = jFileChooser.showOpenDialog(jFrame);
            if (result == jFileChooser.APPROVE_OPTION) {
                file2 = jFileChooser.getSelectedFile();
            } else {

            }
        } else  if(e.getActionCommand().equals("begin")){
            LovingAction lovingAction = new LovingAction();
            System.out.println(file1.getName());
            System.out.println(file2.getName());

        }
    }
}

