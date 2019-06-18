package com.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/6/17
 * @Author: qinzhu
 */
public class 最长回文子串 {
    public static void main(String[] args) {
        String[] s = {"babadada", "", "ccc", "bbdddbda", "eeeee"};
        for (String ex : s) {
            System.out.println(longestPalindrome(ex));
        }
//        longestPalindrome("ccc");
    }

    private static String result = "";
    private static String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        result = String.valueOf(s.charAt(0));
        for (int i = 0; i < s.length() - 1; i++) {
            // 1.回文串的中心在i字符上
            centerExpande(s, i, i);
            // 2.回文串的中心在字符i和i+1之间
            centerExpande(s, i, i + 1);
        }
        return result;
    }

    private static void centerExpande(String s, int low, int high) {
        while (low >= 0 && high < s.length()) {
            if (s.charAt(low) == s.charAt(high)) {
                if ((high - low + 1) > result.length()) {
                    result = s.substring(low, high + 1);
                }
                low--;
                high++;
            } else {
                return;
            }
        }
    }

    public static String longestPalindrome_ugly(String s) {
        if (s == null || "".equals(s)) {
            return "";
        } else if (s.length() == 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        String maxSubstring = s.charAt(0) + "";
        Map<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            ArrayList<Integer> list = map.get(chars[i]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(chars[i], list);
            }
            list.add(i);
        }
        for (Character ch : map.keySet()) {
            ArrayList<Integer> list = map.get(ch);
            int size = list.size();
            if (size == 0 || (list.get(size - 1) - list.get(0) < maxSubstring.length())) {
                continue;
            }

            while (true) {
                Integer firstIndex = list.get(0);
                Integer index = list.get(--size);
                if (index.equals(firstIndex)) {
                    if (list.size() <= 2) {
                        break;
                    } else {
                        list.remove(0);
                        size = list.size();
                        continue;
                    }
                }
                boolean find = true;
                int m = firstIndex;
                int n = index;
                while (find) {
                    if (chars[++m] != chars[--n]) {
                        find = false;
                    }
                    if (m >= n) {
                        break;
                    }
                }
                if (find) {
                    String substring = s.substring(firstIndex, index + 1);
                    if (substring.length() > maxSubstring.length()) {
                        maxSubstring = substring;
                        if (maxSubstring.length() == (list.get(list.size() - 1) - firstIndex + 1)) {
                            break;
                        }
                    }
                }
            }
        }
        return maxSubstring;
    }
}
