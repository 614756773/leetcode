package com.medium;


import com.util.InputHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2019/6/6
 * @Author: qinzhu
 */
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        String s = InputHelper.inputStr();
        lengthOfLongestSubstring(s);
    }

    /**
     * 滑动窗口的思想
     * @param s
     */
    private static void lengthOfLongestSubstring(String s) {
        int head = 0, tail = 0, len = s.length();
        int result = 0;
        Set<Character> set = new HashSet<>();
        while (head < len && tail < len) {
            if (!set.contains(s.charAt(tail))) {
                set.add(s.charAt(tail++));
            }else {
                set.remove(s.charAt(head++));
            }
            result = Math.max(result, tail - head);
        }
        System.out.println(result);
    }


    /**
     * 暴力解法，如果遇到重复只是头部滑动，尾巴又重新生成= =
     * @param s
     */
    private static void lengthOfLongestSubstring_ugly(String s) {
        int check = check(s);
        if (check != -1) {
            System.out.println(check);
            return;
        }


        // 初始化容器 标志
        char[] chars = s.toCharArray();
        int head = 0, finalMaxLen = 0;
        Set<Character> set = new HashSet<>();
        boolean isLastChar = false;
        // 主逻辑
        while (true) {

            int maxLen = 0;
            for (int tail = head; tail < chars.length; tail++) {
                int oldSize = set.size();
                set.add(chars[tail]);
                int newSize = set.size();
                if (oldSize == newSize) {
                    head++;
                    set.clear();
                    break;
                }
                maxLen++;
                if (tail == chars.length - 1) {
                    isLastChar = true;
                }
            }
            finalMaxLen = maxLen > finalMaxLen ? maxLen : finalMaxLen;

            if (head == chars.length || isLastChar) {
                break;
            }
        }
        System.out.println(finalMaxLen);
    }

    private static int check(String s) {
        if (s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }
        return -1;
    }
}
