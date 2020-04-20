package com.medium;

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

    private char[][] grid;
    private int ans = 0;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length) {
            return;
        }
        if (grid[x][y] != '1') {
            return;
        }

        grid[x][y] = 'a';
        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}
