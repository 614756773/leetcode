package com.swordoffer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: qinzhu
 * @since: 2020/09/25
 * 原地排序动画：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/jian-zhi-offer-mian-shi-ti-jing-xuan-tu-jie-03-shu/
 */
public class 数组中重复的数字 {
    /**
     * hash set
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>((int)(nums.length / 0.75) + 1);
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }

    /**
     * hash表
     */
    public int findRepeatNumber_u(int[] nums) {
        int[] hash = new int[100000];
        for (int num : nums) {
            if (hash[num] != 0) {
                return num;
            }
            hash[num]++;
        }
        return -1;
    }

    /**
     * 原地排序 （使用原地排序需要有前置条件：数组长度不小于元素的最大值，和hash表一样）
     */
    public int findRepeatNumber_uu(int[] nums) {
        int tmp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                continue;
            }

            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
        }

        return -1;
    }
}
