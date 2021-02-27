package com.hard.数组;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: hotpot
 * @since: 2021/02/27
 */
public class 滑动窗口的最大值 {
    public static void main(String[] args) {
        滑动窗口的最大值 o = new 滑动窗口的最大值();
        System.out.println(Arrays.toString(o.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums.length < k) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];
        int resIdx = 0;
        // 初始双端队列，其内的元素为nums中的下标
        Deque<Integer> deque = new LinkedList<>();
        // i为窗口的有边界指针
        for (int i = 0; i < nums.length; i++) {
            // 保证双端队列中存储的是窗口内，最大元素的下标
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            // 原来的最大值已经在窗口外了，需要移除
            if (!deque.isEmpty() && deque.peek() + k <= i) {
                deque.removeFirst();
            }

            // i所在的位置，已经能构成窗口了
            if (i + 1 - k >= 0){
                result[resIdx++] = nums[deque.peek()];
            }
        }
        return result;
    }
}
