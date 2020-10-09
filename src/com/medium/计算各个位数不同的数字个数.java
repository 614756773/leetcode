package com.medium;

/**
 * @author qinzhu
 * @since 2020/10/9
 * 思路一，动态规划：
 * 定义dp[n]为从0到10^n - 1所包含的各个位数都不相同的数字个数
 * <p>
 * dp[0] = 1             即数字0
 * dp[1] = 10，          即数字0,1,2,3,4,5,6,7,8,9
 * dp[2] = dp[1] + 9 * 9，
 * 首先dp[2]肯定也包含dp[1]（也就是只有一位数）的情况。然后当是两位数的情况时，
 * 第一位数可以选择[1,9]共9个数，第二位数可以选择[0,9] - 1共9个数（之所以要减去1是因为第二位数不能和第一位数相同）
 * dp[3] = dp[2] + 9 * 9 * 8
 * dp[4] = dp[3] + 9 * 9 * 8 * 7
 * dp[n] = dp[n - 1] + 9 * 9 ......(10 - n + 1)
 * <p>
 * 通过递推公式（状态转移方程）还可以得出一个结论，dp[n]是有最大值的，也就是dp[10]。后面的dp[11],dp[12]之类的值都和dp[10]相同了
 */
public class 计算各个位数不同的数字个数 {
    public static void main(String[] args) {
        System.out.println(new 计算各个位数不同的数字个数().countNumbersWithUniqueDigits(2));
    }
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; i++) {
            if (i > 10) {
                return dp[10];
            }
            dp[i] = dp[i - 1] + help(i);
        }
        return dp[n];
    }

    private int help(int t) {
        int result = 9;
        for (int i = 0, cur = 9; i < t - 1; i++, cur--) {
            result *= cur;
        }
        return result;
    }
}
