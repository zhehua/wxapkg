package com.hm;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception{
        //AppID
        String wxid = "wx3b0c68aed6149ab6";
        //wxapkg路径
        String base="D:\\data\\wechat\\WeChat Files\\Applet\\wx3b0c68aed6149ab6\\11";
        String wxapkgPath=base+"\\__APP__.wxapkg";
        //解密后保存路径
        String decryptWxapkgPath=base+"\\java.wxapkg";
      //  DecryptWxapkg.decrypt(wxid,wxapkgPath,decryptWxapkgPath);

        String outPath=base+"\\"+wxid;
        UnpackWxapkg.run(new File(decryptWxapkgPath),new File(outPath));
    }
}