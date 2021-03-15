package com.hard.栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: hotpot
 * @since: 2021/03/15
 */
public class 柱状图中最大的矩形 {
    public static void main(String[] args) {
        柱状图中最大的矩形 o = new 柱状图中最大的矩形();
        int res = o.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3, 9, 2, 1, 10});
        System.out.println(res);
    }

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        if (heights == null || heights.length == 0) {
            return res;
        }

        // 在首尾加入哨兵
        int[] data = new int[heights.length + 2];
        data[0] = -1;
        System.arraycopy(heights, 0, data, 1, heights.length);
        data[data.length - 1] = -1;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < data.length; i++) {
            int curHeight = data[i];
            while (!stack.isEmpty() && curHeight < data[stack.peek()]) {
                int mid = stack.pop();
                int left = stack.peek();
                int right = i;
                res = Math.max(res, (right - left - 1) * data[mid]);
            }
            stack.push(i);
        }

        return res;
    }
}
