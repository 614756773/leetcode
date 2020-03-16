package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/16
 */
public class 压缩字符串 {
    public static void main(String[] args) {
        System.out.println(new 压缩字符串().compressString("aabbc"));
    }

    public String compressString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String origin = s;
        s = s + "#";
        char[] array = s.toCharArray();
        char currentChar = array[0];
        int currentCount = 0;
        int index = 0;
        StringBuilder res = new StringBuilder();

        while (true) {
            if (array[index] == currentChar) {
                currentCount++;
                index++;
            } else {
                res.append(currentChar).append(currentCount);
                currentChar = array[index];
                currentCount = 0;
            }
            if (index == array.length) {
                break;
            }
        }
        if (res.length() < origin.length()) {
            return res.toString();
        }
        return origin;
    }
}
