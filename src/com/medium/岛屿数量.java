package com.medium;

import java.util.LinkedList;

/**
 * @author hotpot
 * @since 2020-04-20 21:28:45
 * 思路：
 * 还是采用感染的方法，不过不同于`地图分析`那题，那道题使用的是bfs，本题使用dfs
 * 首先最外层遍历每一个节点，当遇到是小岛（即值为1）时，递归感染这个小岛周围的岛，然后结果+1
 * 递归感染小岛时不遍历整个二维数组，只找上下左右四个点
 */
public class 岛屿数量 {
    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','1','0','0'},{'1','1','0','1','0'},
                {'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(new 岛屿数量().numIslands(grid));
    }
    private int ans = 0;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return ans;
        }
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '0') {
                    continue;
                }
                ans++;
                bfsInfection(grid, row, col);
            }
        }
        return ans;
    }

    private void bfsInfection(char[][] grid, int row, int col) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] pair = queue.removeFirst();
            int i = pair[0];
            int j = pair[1];
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
                continue;
            }
            if (grid[i][j] == '0') {
                continue;
            }
            grid[i][j] = '0';
            queue.addLast(new int[]{i, j - 1});
            queue.addLast(new int[]{i, j + 1});
            queue.addLast(new int[]{i - 1, j});
            queue.addLast(new int[]{i + 1, j});
        }
    }

    private void dfsInfection(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        dfsInfection(grid, row, col - 1);
        dfsInfection(grid, row, col + 1);
        dfsInfection(grid, row - 1, col);
        dfsInfection(grid, row + 1, col);
    }
}
