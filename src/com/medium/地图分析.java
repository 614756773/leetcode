package com.medium;

import java.util.LinkedList;

/**
 * @author qinzhu
 * @since 2020/3/30
 * 思路：使用bfs，从所有陆地开始出发，同时一圈一圈的向外`感染`，每感染一圈与陆地距离就+1，最后的数据就是结果
 */
public class 地图分析 {
    private final static int ocean = 0;
    private final static int land = 1;
    private LinkedList<Point> queue = new LinkedList<>();

    public int maxDistance(int[][] grid) {
        boolean hasOcean = false;
        // 先将所有陆地入队
        int limit = grid.length;
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                if (grid[i][j] == land) {
                    enqueue(new Point(i, j));
                } else {
                    hasOcean = true;
                }
            }
        }
        if (!hasOcean || queue.size() == 0) {
            return -1;
        }

        // 方向向量，用于向上下左右四个位置扩散
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};
        Point p = null;
        while (!queue.isEmpty()) {
            p = queue.removeFirst();
            // 将p周围的海洋入队，并且标志p与海洋的距离为上一次+1
            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];
                if (newX < 0 || newX >= limit || newY < 0 || newY >= limit) {
                    continue;
                }
                int value = grid[newX][newY];
                // 只能用不等于ocean，不能用等于land，因为在后续的操作中grid的值还会出现1,2,3等
                if (value != ocean) {
                    continue;
                }
                grid[newX][newY] = grid[p.x][p.y] + 1;
                enqueue(new Point(newX, newY));
            }
        }
        // 最后的p的距离就是结果
        return grid[p.x][p.y] - 1;
    }

    private void enqueue(Point point) {
        queue.addLast(point);
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
