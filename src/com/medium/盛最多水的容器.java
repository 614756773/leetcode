package com.medium;

/**
 * @Date: 2019/6/21
 * @Author: qinzhu
 * 思路：
 * 使用双指针，一头一尾，
 * 每次移动都保留height值更高的指针，让后往中间靠拢直到两指针相遇
 * 在移动的过程中计算出盛水的容量，如果大于最大值则替换
 */
public class 盛最多水的容器 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] a = new int[]{2, 3, 10, 5, 7, 8, 9};
        System.out.println(maxArea(a));
    }

    private static int maxArea(int[] height) {
        int ans = 0;
        int head = 0;
        int tail = height.length - 1;
        while(head > tail) {
            int s = (tail - head) * Math.min(height[head], height[tail]);
            ans = Math.max(ans, s);
            if(height[head] > height[tail]) {
                tail--;
            } else {
                head++;
            }
        }
        return ans;
    }

}
