package com.guosai.countMoney;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qiyang on 2015/8/22.
 */
public class FileUtil {
    public static void writeDiffNameLog(String text) {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File home_dir = FileSystemView.getFileSystemView().getHomeDirectory();
        String path = home_dir.getAbsolutePath();
        File file = new File( path +File.separator + "发票抬头不一致.txt");
        FileWriter fileWriter =null;
        try {
             fileWriter=new FileWriter(file,true);
            fileWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
