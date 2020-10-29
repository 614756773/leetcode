package com.medium;

/**
 * @author qinzhu
 * @since 2020/10/29
 * 思路：转换成最长公共子序列问题，使用dp
 */
public class 两个字符串的删除操作 {
    public static void main(String[] args) {
        System.out.println(new 两个字符串的删除操作().minDistance("seasdasdasda", "easdasdtasda"));
    }
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int l = longestCommonSubsequence(word1, word2);
        return word1.length() - l + word2.length() - l;
    }

    /**
     * 定义dp[i][j]为word1[0,i-1]和word2[0,j-1]的最长公共子序列
     */
    private int longestCommonSubsequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // base case为dp[0][.]与dp[.][0]，他们的值都为0
        // 从第0到第-1个字符肯定不存在嘛，更快公共子序列了
        int[][] dp = new int[m + 1][n + 1];

        // 跳过i=0，j=0,它们为base case
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
