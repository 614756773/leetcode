package com.medium;

import java.util.Arrays;

/**
 * @author hotpot
 * @since 2020-06-09 20:19:57
 */
public class 最长上升子序列 {
    /**
     * 方法一
     * 使用动态规划
     * 时间复杂度：O(N^2)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = 1;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    result = Math.max(result, dp[i]);
                }
            }
        }
        return result;
    }

    /**
     * 方式二
     * 使用二分查找：像windows的蜘蛛牌游戏一样，把牌分成多个有序的堆，最后堆的数量就是这道题的答案
     * 时间复杂度：O(NlogN)
     */
    public int lengthOfLIS_(int[] nums) {
        int stackNum = 0;

        // top[0]的值表示第1堆最上面的牌，top[1]的值表示第2堆最上面的牌.....
        int[] top = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];
            // 二分搜索确定应该放在哪一堆
            int left = 0, right = stackNum;
            while (left < right) {
                int mid = (left + right) / 2;
                if (poker < top[mid]) {
                    right = mid;
                } else if (poker > top[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // 没找到合适的牌堆，新建一堆
            if (left == stackNum) {
                stackNum++;
            }
            // 放入牌堆顶部
            top[left] = poker;
        }
        return stackNum;
    }
}
