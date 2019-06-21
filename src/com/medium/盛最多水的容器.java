package com.medium;

/**
 * @Date: 2019/6/21
 * @Author: qinzhu
 */
public class 盛最多水的容器 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] a = new int[]{2, 3, 10, 5, 7, 8, 9};
        System.out.println(maxArea(a));
    }

    private static int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;
        int max = 0;
        while (head < tail) {
            int w = tail - head;
            int h = Math.min(height[head], height[tail]);
            int s = w * h;
            max = s > max ? s : max;

            if (height[tail] > height[head]) {
                head++;
            } else {
                tail--;
            }
        }
        return max;
    }

}
