package com.guosai.countMoney;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by qiyang on 15-8-18.
 */
public class LovingAction {
    public void action(String file1,String file2){
        POIFSFileSystem fs1;
        POIFSFileSystem fs2;
        try {
            fs1 = new POIFSFileSystem(new FileInputStream(file1));
            fs2 = new POIFSFileSystem(new FileInputStream(file2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
