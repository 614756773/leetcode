package com.simple;

import java.util.Arrays;

/**
 * @author qinzhu
 * @since 2020/10/26
 * 思路：
 * 先将数组进行排序，然后遍历排序后的数组
 * 数组的下标值n，就是有n个数小于当前数字，但是要注意两个数相同的情况
 * 若两个数相同，则当前数的结果应该和前一个相同
 * e.g.
 * 原数组为【8,1,2,2,3】
 * 排序后为【1,2,2,3,8】
 * 1的下标为0，所以有0个小于1的数字
 * 2的下标为1，所以有1个小于2的数字
 * 2的下标为1，并且和前一个数相同，所以有1个小于2的数字
 * 3的下标为3，所以有3个小于3的数字
 * 8的下标为4，所以有4个小于8的数字
 */
public class 有多少小于当前数字的数字 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{8,1,2,2,3})));
    }

    /**
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        // 记录原数组的顺序
        int[] original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
        Arrays.sort(nums);
        // 使用hash表存储每个元素的结果（也就是有多少个数，小于当前的数字）
        int[] hash = new int[101];

        int preValue = -1;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value == preValue) {
                hash[value] = hash[preValue];
            } else {
                hash[value] = i;
            }
            preValue = value;
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < original.length; i++) {
            res[i] = hash[original[i]];
        }
        return res;
    }
}
