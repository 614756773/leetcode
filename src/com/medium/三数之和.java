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

        int[] nums3 = new int[]{0, 0, 0};
        System.out.println(threeSum(nums3));

        int[] nums4 = new int[]{0, 0};
        System.out.println(threeSum(nums4));

        int[] nums5 = new int[]{1, 2, -2, -1};
        System.out.println(threeSum(nums5));

        int[] nums6 = new int[]{7, -1, 14, -12, -8, 7, 2, -15, 8, 8, -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 1, -14, 4, 3, 10, -5, 2, 1, 6, 11, 2, -2, -5, -7, -6, 2, -15, 11, -6, 8, -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 10, 14, 9, -8, -6, 4, -6, 11, 12, -15, 7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 0, 5, 14, -1, 14, 3, 8, 10, -8, 8, -5, -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 14, -2, -5, -11, 1, 3, -6};
        System.out.println(threeSum(nums6));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            map.merge(num, 1, (oldValue, newValue) -> oldValue + newValue);
//        }

        for (int i = 0; i < nums.length; i++) {
            int number1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int number2 = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int number3 = nums[k];
                    if (number1 + number2 + number3 == 0) {
//                        result.add(Arrays.asList(number1, number2, number3));
                        map.putIfAbsent(key(number1, number2, number3), Arrays.asList(number1, number2, number3));
                    }
                }
            }
        }
/*        for (int number1 : map.keySet()) {
            Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
            for (Map.Entry<Integer, Integer> entry : entries) {
                if (entry.getValue() > 0) {
                    int number2 = entry.getKey();
                    int number3 = -number1 - number2;
//                    if (number1 == number2 && entry.getValue() < 2) {
//                        continue;
//                    }
//                    if (number1 == number3 && map.get(number3) < 2) {
//                        continue;
//                    }
//                    if (number2 == number3 && map.get(number3) < 2) {
//                        continue;
//                    }
//                    if (number1 == number2 && number2 == number3 && map.get(number3) < 3) {
//                        continue;
//                    }
                    Integer canUseCount = map.get(number3);
                    if (canUseCount != null && canUseCount > 0) {
                        map.put(number1, map.get(number1) - 1);
                        map.put(number2, map.get(number2) - 1);
                        map.put(number3, map.get(number3) - 1);
                        result.add(Arrays.asList(number1, number2, number3));
                    }
                }
            }
        }*/
        return new ArrayList<>(map.values());
    }

    private static String key(int number1, int number2, int number3) {
        int max = Math.max(number1, number2);
        max = Math.min(max, number3);
        int min = Math.min(number1, number2);
        min = Math.min(min, number3);
        return max + "-" + min + "-" + (-max - min);
    }

}
