package com.medium;

import java.util.LinkedList;

/**
 * @author qinzhu
 * @since 2020/4/15
 * 思路：
 * 先把1替换成-1，因为我后面要用到步数1,2,3....
 * 然后把所有0入队，在挨个从0去感染上下左右的-1，让他们步数为自己的值+1
 * 接着把被感染的点入队，然后重复操作继续去感染上下左右的-1点以及入队，直到完全没有-1的点了
 */
public class _01矩阵 {
    public int[][] updateMatrix(int[][] matrix) {
        LinkedList<Point> queue = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int val = matrix[i][j];
                if (val == 1) {
                    matrix[i][j] = -1;
                } else {
                    queue.addLast(new Point(i, j, val));
                }
            }
        }

        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Point point = queue.removeFirst();
            for (int i = 0; i < 4; i++) {
                int newX = point.x + dx[i];
                int newY = point.y + dy[i];
                if (newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix[newX].length) {
                    continue;
                }
                if (matrix[newX][newY] != -1) {
                    continue;
                }
                matrix[newX][newY] = point.val + 1;
                queue.addLast(new Point(newX, newY, matrix[newX][newY]));
            }
        }
        return matrix;
    }

    static class Point {
        int x;
        int y;
        int val;

        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
