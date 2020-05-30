package com.simple;

/**
 * @author qinzhu
 * @since 2020/5/14
 */
public class 只出现一次的数字 {
    public int singleNumber(int[] nums) {
        int num = nums[0];
        if (nums.length == 1) {
            return num;
        }
        for (int i = 1; i < nums.length; i++) {
            num ^= nums[i];
        }
        return num;
    }
}
