package com.hm.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
    //合并两个byte数组
    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }
    //将byte数组保存成成文件
    public static void write(File outFile,byte[] buf) throws Exception {
        if (!outFile.getParentFile().exists() && !outFile.getParentFile().mkdirs()) {
            throw new RuntimeException("无法创建文件夹:" + outFile.getParentFile().getAbsolutePath());
        }
        FileOutputStream out = new FileOutputStream(outFile);
        try{
            out.write(buf);
        }finally {
            out.close();
        }
    }
}
