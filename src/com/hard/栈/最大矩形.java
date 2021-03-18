package com.hard.栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: hotpot
 * @since: 2021/03/18
 */
public class 最大矩形 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int res = 0;
        for (int row = 0; row < matrix.length; row++) {
            int[] heights = computeHeights(matrix, row);
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }

    /**
     * 最大的矩形面积
     */
    private int largestRectangleArea(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int mid = stack.pop();
                int left = stack.peek();
                int right = i;
                res = Math.max(res, (right - left - 1) * heights[mid]);
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 以第bottomRow行为低，计算每列的高度
     */
    private int[] computeHeights(char[][] matrix, int bottomRow) {
        // 首尾填充0作为哨兵，使用单调栈时更易进行计算
        int colNums = matrix[bottomRow].length;
        int[] ans = new int[colNums + 2];
        ans[0] = 0;
        ans[ans.length - 1] = 0;
        for (int col = 0; col < colNums; col++) {
            int height = 0;
            for (int row = bottomRow; row >= 0; row--) {
                if (matrix[row][col] == '0') {
                    break;
                }
                height++;
            }
            ans[col + 1] = height;
        }
        return ans;
    }
}
