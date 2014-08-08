package com.tianxin.wechat.platform.utils;

/**
 * @author snowway
 * @since 6/13/14 12:22
 */
public class HexUtils {

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};


    /**
     * 转换为16进制字符串
     *
     * @param content byte content
     * @return hex string
     */
    public static String toHexString(byte[] content) {
        StringBuilder sb = new StringBuilder(content.length * 2);
        for (byte item : content) {
            sb.append(HEX_DIGITS[(item & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[item & 0x0f]);
        }
        return sb.toString();
    }
}
