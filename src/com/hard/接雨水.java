package com.hard;

import java.util.LinkedList;

public class 接雨水 {
    public static void main(String[] args) {
        System.out.println(new 接雨水().trap(new int[]{4, 3, 1, 0, 1, 2, 4}));
    }

    public int trap(int[] height) {
        int result = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        if (height == null || height.length < 2) {
            return 0;
        }

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int mid = stack.pop();
                while (!stack.isEmpty() && height[stack.peek()] <= height[mid]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int num = (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - height[mid]);
                    result += num;
                }
            }
            stack.push(i);
        }

        return result;
    }
}
