package com.medium;

import java.util.Arrays;

/**
 * @author qinzhu
 * @since 2020/10/28
 * 参考：https://mp.weixin.qq.com/s/ZhPEchewfc03xWv9VP3msg
 */
public class 最长公共子序列 {

    public static void main(String[] args) {
        System.out.println(new 最长公共子序列().longestCommonSubsequence("qasdwe", "qeasd"));
        System.out.println(new 最长公共子序列().longestCommonSubsequence_("qasdwe", "qeasd"));
    }

    /**
     * 方式一：备忘录 自顶向下
     * 定义dp[s1,0,s2,0]表示为，字符串s1从第0个字符开始与字符串s2从第0个字符开始的最长公共子序列长度
     * dp[s1,0,s2,1]表示为，字符串s1从第0个字符开始与字符串s2从第1个字符开始的最长公共子序列长度
     * dp[s1,len(s1),s2,...]和dp[s1,..,s2,len(s2)]，这些值肯定就是base case，其值为0 （因为len(s1)和len(s2)所在下标的字符是不存在的）
     * <p>
     * 状态转移：
     * a. 当i位置上的字符和j位置上的字符相等时：dp[s1,i,s2,j] = 1 + dp[s1,i+1.s2,j+1]
     * b. 当i位置上的字符和j位置上的字符不相等时：dp[s1,i,s2,j] = Max(dp[s1,i,s2,j+1], dp[s1,i+1,s2,j], dp[s1,i+1,s2,j+1])
     * 精简一下则是 dp[s1,i,s2,j] = Max(dp[s1,i,s2,j+1], dp[s1,i+1,s2,j])
     * 因为dp[s1,i+1,s2,j+1]肯定被前两种情况包含在内了
     * <p>
     * e.g.
     * s1为"qwe"，s2为"qe"
     * dp[s1,0,s2,0] = 1 + dp[s1,1,s2,1]
     * <p>
     * dp[s1,1,s2,1] = Max(dp[s1,1,s2,2], dp[s1,2,s2,1])
     * dp[s1,1,s2,2]的值为0，因为s2的第2个字符不存在。dp[s1,2,s2,1]值为1，因为s1的第2个字符等于s2的第一个字符........
     * <p>
     * 最终得出dp[s1,0,s2,0]为2
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] memo = new int[text1.length()][text2.length()];
        // -1表示未曾计算
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(text1, 0, text2, 0, memo);
    }

    private int dp(String text1, int i, String text2, int j, int[][] memo) {
        // base case
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }
        // 先从备忘录查，避免重复计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            int res = 1 + dp(text1, i + 1, text2, j + 1, memo);
            memo[i][j] = res;
            return res;
        }

        int res = Math.max(dp(text1, i, text2, j + 1, memo), dp(text1, i + 1, text2, j, memo));
        memo[i][j] = res;
        return res;
    }

    /**
     * 方式二：自底向上
     * 定义dp[i][j]为：text1[0..i-1]与text2[0..j-1]的最长公共子序列长度    （text[0..i-1]表示从text的第0个字符到第i-1个字符，首位都包含）
     * 所以若有两个字符长度分别为m和n，我们要求的结果就是dp[m][n]
     *
     * base case：dp[0][..] = dp[..][0] = 0         （因为dp[0][..]指的是从text1的第-1个字符串开始，结果值肯定为0。 同理dp[..][0]也为0）
     * 状态转移：
     *     如果text1的第i - 1个字符与text2的第j - 1个字符相等，则dp[i][j] = 1 + dp[i-1][j-1]
     *     如果text1的第i - 1个字符与text2的第j - 1个字符不等，则dp[i][j] = Max(dp[i-1][j], dp[i][j-1])
     *
     */
    public int longestCommonSubsequence_(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // base case：dp[0][..]、dp[..][0]的值已经为0
        int[][] dp = new int[m + 1][n + 1];

        // i=0.j=0不用计算了，因为dp[0][..]和dp[..][0]作为base case
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
