package com.medium;

import java.util.Arrays;

/**
 * @author qinzhu
 * @since 2020/11/10
 * 思路： 找拐点 && 交换 && 翻转
 *
 * 1. 从后往前依次比较，记录左侧的数下标为i，右侧的数下标为j，找到第一个相邻的升序对，也就是i与j挨着的并且a[i] > a[j]
 * 这时候可以断定[j,end]一定是降序的（原因嘛，可以把这些点画成折线图，可以发现j在图中一定是拐点）
 * e.g.  a=[1,2,1]  j=2,一直往前找都没有找到满足条件的i。于是j前移，j=1，然后找到第一个满足条件的i，i=0，这个时候j=1,end=2他们是降序的
 * 2. 然后在[j,end]子序列从后往前找，找到第一个k使得a[k] > a[i]
 * 3. 交换a[k]与a[i]，这个时候[j,end]一定是降序的，为了满足题目的要求所以得把它们变为升序，逆转一下即可
 */
public class 下一个排列 {
    public static void main(String[] args) {
        int[] nums = {1,3,2};
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < 30; i++) {
            new 下一个排列().nextPermutation(nums);
            System.out.println(Arrays.toString(nums));
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int i = nums.length - 2;
        int j = nums.length - 1;
        // 寻找第一个紧邻的升序对i,j
        while (i >= 0 && j >= 0) {
            if (nums[i] < nums[j]) {
                break;
            }
            i--;
            j--;
        }

        // 找到最前面了，也没有找到紧邻的升序对，说明整个序列都是降序的
        if (j == 0) {
            flip(nums, 0);
            return;
        }

        // [j,end]一定是降序的，此时找到其中第一个大于a[i]的数，将它们交换。
        // 然后可以确定的是[j,end]一定是降序的，再将其翻转
        for (int k = nums.length - 1; k >= j; k--) {
            if (nums[k] > nums[i]) {
                int tmp = nums[k];
                nums[k] = nums[i];
                nums[i] = tmp;
                flip(nums, j);
                return;
            }
        }
    }

    private void flip(int[] nums, int start) {
        if (start >= nums.length - 1) {
            return;
        }
        for (int i = start,j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
