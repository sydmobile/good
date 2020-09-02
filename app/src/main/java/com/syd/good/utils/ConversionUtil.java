package com.syd.good.utils;

import java.nio.charset.StandardCharsets;

/**
 * 说明：转换工具类
 *
 * @author sunyidong
 * @version 1.0
 * 创建时间 2018/9/6 9:54
 */
public class ConversionUtil {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * 字节数组转十六进制字符串
     *
     * @param bytes 字节数组
     * @return 16 进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);

    }


    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hexString 16 进制字符串
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    private static int charToByte(char c) {

        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] encodeBase64String(String password){
        byte[] b = new byte[password.length()];

        for (int i = 0 ; i<password.length(); ++i){
            b[i] = (byte) password.charAt(i);

        }
        return b;
    }


    public String encodeBase64String(byte[] b){
        return new String(b, StandardCharsets.UTF_8);
    }
}
