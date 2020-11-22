package com.simple;

/**
 * @author: qinzhu
 * @since: 2020/11/22
 * 思路：hash
 * 异位词的定义「两个字符串的字符经过重新排列后能变成完全相等的字符串，如abc何cba」
 */
public class 有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }

        for (int v : hash) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
