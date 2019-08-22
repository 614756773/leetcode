package com.hard;

/**
 * @Date: 2019/6/19
 * @Author: qinzhu
 */
public class 正则表达式匹配 {
    public static void main(String[] args) {
//        String[] text = new String[]{"aa", "aa", "*a", ""};
//        String[] pattern = new String[]{".", "*."};
//        System.out.println(isMatch(text[2], pattern[1]));
//        System.out.println(isMatch_2("aac", "a*c"));
//        System.out.println(isMatch_2("aab", "c*a*b"));
        System.out.println(isMatch("abccb", "abc*"));
    }

    /**
     * 回溯（递归）法
     */
    private static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            // 1.pattern要跳2是因为不清楚是第一次遇到*，还是由于递归调用导致的*
            // 2.比如：text为aac，pattern为a*c
            // 3.首先text的a和pattern的a相同，并且发现pattern的下一个字符是*，所以接下来比较【ac和a*c】 或者 【ac和c】
            // 4.比较【ac和c】发现不匹配，这个递归结束。
            // 5.比较【ac和a*c】发现首字符匹配，并且发现pattern下一个字符是*，这个时候处理就和3相同了，所以接下来比较【c和a*c】 或者 【c和c】
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    private static Result[][] memo;
    private static boolean isMatch_2(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp_2(0, 0, text, pattern);
    }
    /**
     * 动态规划：自顶向下 + 备忘录
     */
    private static boolean dp_2(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean firstMatch = pattern.charAt(j) == '.' || i < text.length() && pattern.charAt(j) == text.charAt(i);

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp_2(i, j + 2, text, pattern) ||
                        firstMatch && dp_2(i + 1, j, text, pattern));
            } else {
                ans = firstMatch && dp_2(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    enum Result {
        TRUE, FALSE
    }

    public boolean isMatch_3(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean firstMatch = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || firstMatch && dp[i+1][j];
                } else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

}
