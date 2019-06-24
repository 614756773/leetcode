package com.simple;

/**
 * @Date: 2019/6/24
 * @Author: qinzhu
 */
public class 最长公共前缀 {
    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix(new String[]{}));
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int index = 0;
        while (index < minLen) {
            char tmp = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(index) != tmp) {
                    return sb.toString();
                }
            }
            sb.append(tmp);
            index++;
        }
        return sb.toString();
    }
}
