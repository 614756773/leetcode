package com.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hotpot
 * @since 2020-04-22 22:56:01
 * 参考：<a href="https://leetcode-cn.com/problems/count-number-of-nice-subarrays/solution/java-hua-dong-chuang-kou-xiang-jie-zhi-xing-yong-s/"></a></a>
 * 思路：
 * 首先只找奇数的数字，用他们做窗口，窗口的大小就是k
 * 根据每个窗口的最左边奇数和最右边奇数能够确定这个窗口，能配合左右两侧相邻的偶数组成n个优美子数组
 * 然后滑动窗口，把每个窗口能算出的n个优美子数组累加起来就是结果
 */
public class 统计优美子数组 {
    public static void main(String[] args) {
        System.out.println(new 统计优美子数组().numberOfSubarrays(new int[]{1, 1, 2, 1, 1, 1}, 3));
    }
    public int numberOfSubarrays(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int ans = 0;
        List<Integer> odds = new ArrayList<>(nums.length);
        odds.add(-1);

        // 找到所有的奇数位置，使用数组存储，后面会用他们做滑动窗口
        for (int i = 0; i < nums.length; i++) {
            if (isOdd(nums[i])) {
                odds.add(i);
            }
        }
        odds.add(nums.length);
        Integer[] oddArray = new Integer[odds.size()];
        odds.toArray(oddArray);

        // 滑动窗口，并且计算优美数组数量
        if (oddArray.length < k + 2) {
            return 0;
        }
        for (int i = 1; i < oddArray.length - k; i++) {
            int left = i;
            int right = i + k - 1;
            ans += (oddArray[left] - oddArray[left - 1]) * (oddArray[right + 1] - oddArray[right]);
        }

        return ans;
    }

    private boolean isOdd(int num) {
        return (num & 1) == 1;
    }
}
