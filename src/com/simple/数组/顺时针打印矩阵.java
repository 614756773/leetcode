package com.simple.数组;

/**
 * @author: hotpot
 * @since: 2021/02/28
 */
public class 顺时针打印矩阵 {
    public static void main(String[] args) {
        顺时针打印矩阵 o = new 顺时针打印矩阵();
        int[][] m = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        o.spiralOrder(m);
    }

    int[] res;
    int resIdx = 0;
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        res = new int[matrix.length * matrix[0].length];

        // 从坐标为（0，0）的点开始打印
        int start = 0;
        // 能够进行一次打印的条件为：矩阵的行数和列数都大于start*2
        while (matrix.length > 2 * start && matrix[0].length > 2 * start) {
            printOnce(matrix, start);
            start++;
        }
        return res;
    }

    private void printOnce(int[][] matrix, int start) {
        int endX = matrix[0].length - 1 - start;
        int endY = matrix.length - 1 -start;

        // case 0:向右打印
        for (int x = start; x <= endX; x++) {
            res[resIdx++] = matrix[start][x];
        }

        // 至少要有两行才能向下打印
        // case 1:向下打印
        if (endY > start) {
            for (int y = start + 1; y <= endY; y++) {
                res[resIdx++] = matrix[y][endX];
            }
        }

        // 至少得有两行两列
        // case 2:向左打印
        if (endY > start && endX > start) {
            for (int x = endX - 1; x >= start; x--) {
                res[resIdx++] = matrix[endY][x];
            }
        }

        // 首先肯定能够向左打印，然后还得有3行
        // case 3:向上打印
        if (endX > start && endY - 1 > start) {
            for (int y = endY - 1; y > start; y--) {
                res[resIdx++] = matrix[y][start];
            }
        }
    }
}
