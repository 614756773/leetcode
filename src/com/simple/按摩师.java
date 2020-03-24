package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/24
 */
public class 按摩师 {
    public static void main(String[] args) {
        System.out.println(new 按摩师().massage(new int[]{2, 1, 4, 5, 3, 1, 1, 3}));
    }

    /**
     * 状态转移公式：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1])
     * dp[i][1] = dp[i-1][0] + num[i]
     *
     * dp[a][0] 表示考虑前a个预约时，第a个预约不接收时的总时间
     * dp[a][1] 表示考虑前a个预约时，第a个预约接收时的总时间
     */
    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int currentNo, currentYes, result = 0;
        // {pre_no}表示考虑前i个预约时，第i个预约不接收的总时间，初始化时i为0
        int preNo = 0;
        // {pre_yes}表示考虑前i个预约时，第i个预约接收的总时间，初始化时i为0
        int preYes = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentNo = Math.max(preNo, preYes);
            currentYes = preNo + nums[i];
            result = Math.max(currentNo, currentYes);
            preNo = currentNo;
            preYes = currentYes;
        }
        return result;
    }
}
