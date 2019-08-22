package com.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/6/24
 * @Author: qinzhu
 */
public class 三数之和 {
    public static void main(String[] args) {
        int[] nums0 = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums0));

        int[] nums = new int[]{};
        System.out.println(threeSum(nums));

        int[] nums2 = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums2));

        int[] nums3 = new int[]{0, 0, 0, 0};
        System.out.println(threeSum(nums3));

        int[] nums4 = new int[]{0, 0};
        System.out.println(threeSum(nums4));

        int[] nums5 = new int[]{1, 2, -2, -1};
        System.out.println(threeSum(nums5));

        int[] nums6 = new int[]{7, -1, 14, -12, -8, 7, 2, -15, 8, 8, -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 1, -14, 4, 3, 10, -5, 2, 1, 6, 11, 2, -2, -5, -7, -6, 2, -15, 11, -6, 8, -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 10, 14, 9, -8, -6, 4, -6, 11, 12, -15, 7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 0, 5, 14, -1, 14, 3, 8, 10, -8, 8, -5, -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 14, -2, -5, -11, 1, 3, -6};
        System.out.println(threeSum(nums6));
    }

    /**
     * 排序+对撞指针
     * 难点在于去重。好难受啊去重
     */
    private static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果number为正数了，说明后面的数也全是正数，不可能相加为0
            if (nums[i] > 0) {
                break;
            }
            // 去重  当上一轮的number1和这一轮的number1相同时，会找出同样的三个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 去重
                    while (j < k && nums[j] == nums[j + 1]){j++;}
                    while (j < k && nums[k] == nums[k - 1]){k--;}
                    j++;
                    k--;
                } else if(sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                }
            }
        }
        return result;
    }

}
