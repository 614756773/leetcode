package com.simple;

/**
 * @author: qinzhu
 * @since: 2020/11/25
 * 思路：hash + 模拟
 */
public class 上升下降字符串 {
    public static void main(String[] args) {
        System.out.println('a' - 'a');
        char t = 0 + 'a';
        System.out.println(t);
    }
    public String sortString(String s) {
        if (s.length() == 0) {
            return s;
        }

        StringBuilder ret = new StringBuilder();
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hash[c - 'a']++;
        }

        boolean forward = true;
        for (int i = 0; i < s.length(); i++) {
            if (forward) {
                for (int j = 0; j < 26; j++) {
                    int v = hash[j];
                    if (v > 0) {
                        hash[j]--;
                        ret.append((char)(j + 'a'));
                    }
                }
            } else {
                for (int j = 25; j >= 0; j--) {
                    int v = hash[j];
                    if (v > 0) {
                        hash[j]--;
                        ret.append((char)(j + 'a'));
                    }
                }
            }
            forward = !forward;
        }

        return ret.toString();
    }
}
