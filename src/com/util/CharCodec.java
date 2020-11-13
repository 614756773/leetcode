package com.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author qinzhu
 * @since 2020/11/12
 *
 * 该工具的作用是将byte转换为16进制或者8进制
 *
 * 字符集目前使用的要么是Unicode要么是ASCII
 * 针对Unicode字符集常用的字符编码有：UTF-8，GB2312，GBK
 *
 * e.g.
 *      “严”字符的Unicode的码位是U+4E25
 *      若使用GB2312进行编码表示，其结果为1353（16进制），GB2312统一使用2个字节来表示字符
 *      若使用UTF-8进行编码表示，其结果为E4B8A5(16进制)，UTF-8使用1到4个字节来表示字符，此处用的就是3个字节
 */
public class CharCodec {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "严";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(toHex(bytes)));
        System.out.println(Arrays.toString(toBinary(bytes)));
    }

    private static String[] toHex(byte[] value) {
        String[] ret = new String[value.length];
        // 一个字节使用16进制表示的话，需要用2个16进制的数，每4bit用1个16进制数
        for (int i = 0; i < value.length; i++) {
            byte b = value[i];
            // 先转换低4位
            int digit = (b & 15);
            char r = (char) (digit < 10 ? '0' + digit : 'A' + digit);

            // 再转换高4位
            digit = (b >> 4) & 15;
            char l = (char) (digit < 10 ? '0' + digit : 'A' + digit - 10);

            ret[i] = String.valueOf(l) + r;
        }
        return ret;
    }

    private static String[] toBinary(byte[] value) {
        String[] ret = new String[value.length];
        for (int i = 0; i < value.length; i++) {
            byte b = value[i];
            // 从低位开始转换
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                sb.append(b & 1);
                b >>= 1;
            }
            ret[i] = sb.reverse().toString();
        }
        return ret;
    }
}
