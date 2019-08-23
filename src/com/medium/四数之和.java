package com.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/8/23
 * @Author: qinzhu
 */
public class 四数之和 {
    public static void main(String[] args) {
        四数之和 o = new 四数之和();
        List<List<Integer>> lists = o.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        System.out.println(lists);
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        // 1.第1个数
        for (int i = 0; i < nums.length - 3; i++) {
            // 去重，第1个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int num1 = nums[i];
            // 2.第2个数
            for (int j = i + 1; j < nums.length - 2; j++) {
                // 去重，第2个数
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int num2 = nums[j];
                int left = j + 1;
                int right = nums.length - 1;
                // 3.第3个数和第4个数
                while (left < right) {
                    // 去重，第3/第4个数字
                    if (left > j + 1 && nums[left] == nums[left - 1]){
                        left++;
                        continue;
                    }
                    if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                        right--;
                        continue;
                    }
                    int sum = num1 + num2 + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        result.add(Arrays.asList(num1, num2, nums[left], nums[right]));
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
