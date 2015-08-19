package com.guosai.countMoney;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by qiyang on 15-8-19.
 */
public class AreOutPutStream extends OutputStream {
    private JTextArea textArea;

    public AreOutPutStream(JTextArea textArea) {
        super();
        this.textArea = textArea;

    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        textArea.append(new String(b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        textArea.append(new String(b,off,len));
        textArea.setCaretPosition(textArea.getText().length());
    }
}
