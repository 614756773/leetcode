package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/19
 */
public class 最长回文串 {
    public static void main(String[] args) {
        System.out.println(new 最长回文串().longestPalindrome("abccccdd"));
    }

    public int longestPalindrome(String s) {
        int result = 0;
        if (s == null || s.length() < 2) {
            return 1;
        }

        int[] hash = new int[123];
        int exitOdd = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            hash[(int) c]++;
        }

        for (int count : hash) {
            int tmp = count % 2;
            if (tmp == 1) {
                exitOdd = 1;
            }
            result += count / 2 * 2;
        }
        return result + exitOdd;
    }
}
