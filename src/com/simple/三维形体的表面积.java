package com.simple;

public class 三维形体的表面积 {
    public int surfaceArea(int[][] grid) {
        int result = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                int high = grid[x][y];
                if (high == 0) {
                    continue;
                }
                // 表面积等于四周的面积在加上下的面积
                result += 4 * high + 2;
                // 在y方向上要减去重叠的面积，y方向上的第一个不用减
                if (y > 0) {
                    result -= 2 * Math.min(high, grid[x][y - 1]);
                }
                // 在x方向上要减去重叠的面积，x方向上的第一个不用减
                if (x > 0) {
                    result -= 2 * Math.min(high, grid[x - 1][y]);
                }
            }
        }
        return result;
    }
}
