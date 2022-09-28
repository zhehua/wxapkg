package com.hm;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class UnpackWxapkg {
    public static void run(File in, File outDir) throws Exception {
        RandomAccessFile r = new RandomAccessFile(in, "r");
        try {
            if (r.readByte() != (byte) 0xBE) {
                throw new RuntimeException("文件类型错误");
            }
            r.seek(0xE);//前面几个字节不知道含义，估计是校验码之类的；不影响解包
            int fileCount = r.readInt();//文件数量
            ArrayList<WxapkgItem> wxapkgItems = new ArrayList<WxapkgItem>(fileCount);
            for (int i = 0; i < fileCount; i++) {
                int nameLen = r.readInt();//文件名长度
                byte[] buf = new byte[nameLen];
                r.read(buf, 0, nameLen);//文件名
                String name = new String(buf, 0, nameLen);
                int start = r.readInt();//文件内容起始位置
                int length = r.readInt();//文件内容长度
                wxapkgItems.add(new WxapkgItem(name, start, length));
            }
            for (WxapkgItem wxapkgItem : wxapkgItems) {
                File outFile = new File(outDir, wxapkgItem.getName());
                System.out.println(wxapkgItem);
                r.seek(wxapkgItem.getStart());
                byte[] buf = new byte[wxapkgItem.getLength()];
                r.read(buf, 0, wxapkgItem.getLength());
                com.hm.util.FileUtil.write(outFile,buf);
            }
        } finally {
            r.close();
        }
        System.out.println("ok");
    }

}
