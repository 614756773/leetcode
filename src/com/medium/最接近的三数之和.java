package com.medium;

import java.util.Arrays;

/**
 * @Date: 2019/8/22
 * @Author: qinzhu
 */
public class 最接近的三数之和 {
    public static void main(String[] args) {
        System.out.println(new Solution().threeSumClosest(new int[]{0, 2, 1, -3}, 1));
    }
}

/**
 * 排序+对撞指针
 * 对撞指针的核心在于判断是左指针移动还是右指针移动
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int result = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int abs = Math.abs(target - sum);
                if (abs < diff) {
                    diff = abs;
                    result = sum;
                } 

                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return sum;
                }
            }
        }
        return result;
    }
}