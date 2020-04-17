package com.medium;

/**
 * @author qinzhu
 * @since 2020/4/17
 * 思路：
 * 1.计算每个点所能达到的最远距离，如果其中有任何一个能达到的最远距离超过数组长度，则说明能到终点
 * 2.计算每个点所能达到的最远距离时首先要判断该点是否可达，通过前面点的最远距离来进行判断
 * 如果该点不可达，那么该点的最远距离就为0
 * 3.使用一个变量maxDistance来存储在整个系统中所能达到的最远距离，最后maxDistance大于数组长度则能到终点
 */
public class 跳跃游戏 {
    public static void main(String[] args) {
        System.out.println(new 跳跃游戏().canJump(new int[]{1, 0}));
    }

    public boolean canJump(int[] nums) {
        int maxDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxDistance < i) {
                continue;
            }
            maxDistance = Math.max(maxDistance, i + nums[i]);
        }
        return maxDistance >= nums.length - 1;
    }
}