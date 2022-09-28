package com.hm;
import com.hm.util.DecryptUtil;
import com.hm.util.FileUtil;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
public class DecryptWxapkg {

    public static void decrypt(String wxid,String file, String output) throws Exception {
        String initVector = "the iv: 16 bytes";
        String salt = "saltiest";
        String wxapkgFlag = "V1MMWX";
        int wxapkgFlagLen = wxapkgFlag.length();
        byte[] key = DecryptUtil.pbkdf2(wxid.toCharArray(), salt.getBytes("utf-8"), 1000, 32);
        //文件转byte数组
        byte[] dataByte = Files.readAllBytes(Paths.get(file));
        byte[] bytes = new byte[1024];
        //跳过"V1MMWX"6个字节，复制之后的1024字节出来
        System.arraycopy(dataByte, wxapkgFlagLen, bytes, 0, 1024);
        byte[] originData = DecryptUtil.decrypt(bytes, key,initVector);
        // 设置xor密钥, 解密剩下的字节
        int xorKey = 0x66;
        if (wxid.length() >= 2) {
            String substring = wxid.substring(wxid.length() - 2, wxid.length() - 1);
            xorKey = substring.getBytes(StandardCharsets.US_ASCII)[0];

        }
        int len = dataByte.length - (1024 + wxapkgFlagLen);
        byte[] afData = new byte[len];
        System.arraycopy(dataByte, 1024 + wxapkgFlagLen, afData, 0, len);
        byte[] out = new byte[afData.length];
        for (int i = 0; i < afData.length; i++) {
            byte afDatum = afData[i];
            byte a = (byte) (afDatum ^ xorKey);
            out[i] = a;
        }
        byte[] a = new byte[1023];
        System.arraycopy(originData, 0, a, 0, 1023);
        byte[] bytes1 = FileUtil.byteMerger(a, out);
        FileUtil.write( new java.io.File(output),bytes1);
    }
}
