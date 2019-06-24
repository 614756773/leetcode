package com.medium;

import java.util.*;

/**
 * @Date: 2019/6/24
 * @Author: qinzhu
 */
public class 三数之和 {
    public static void main(String[] args) {
        int[] nums = new int[]{};
        System.out.println(threeSum(nums));

        int[] nums2 = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums2));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (a, b) -> a + b);
        }

        // 用于保存本轮中已经被使用过的数据，防止重复
        Set<Integer> memo = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int target = 1 - (nums[i] + nums[i + 1]);
            Integer targetCounts = map.get(target);
            if (target != nums[i] && target != nums[i + 1]) {
                result.add(Arrays.asList(nums[i], nums[i + 1], target));
            } else {
                if (targetCounts != 1) {
                    result.add(Arrays.asList(nums[i], nums[i + 1], target));
                }
            }
        }
        return null;
    }
}
