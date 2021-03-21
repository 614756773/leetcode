package com.medium.并查集;

import java.util.Arrays;

/**
 * @author: hotpot
 * @since: 2021/03/19
 */
public class 岛屿数量 {
    public static void main(String[] args) {
        岛屿数量 o = new 岛屿数量();
        System.out.println(o.numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}}));

    }


    int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int colNums;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        colNums = grid[0].length;
        UnionSet unionSet = new UnionSet(grid);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '0') {
                    continue;
                }
                grid[row][col] = '0';
                for (int i = 0; i < move.length; i++) {
                    int newRow = row + move[i][0];
                    int newCol = col + move[i][1];
                    if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length || grid[newRow][newCol] == '0') {
                        continue;
                    }
                    unionSet.union(getIndex(row, col), getIndex(newRow, newCol));
                }
            }
        }
        return unionSet.getCount();
    }

    private int getIndex(int row, int col) {
        return row * colNums + col;
    }

    static class UnionSet {
        int[] parent;
        int[] ranks;
        int count = 0;

        public UnionSet(char[][] grid) {
            int colNums = grid[0].length;
            parent = new int[grid.length * colNums];
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == '1') {
                        parent[row * colNums + col] = row * colNums + col;
                        count++;
                    }
                }
            }
            ranks = new int[parent.length];
            Arrays.fill(ranks, 0);
        }

        public int findRoot(int x) {
            int root = x;
            while (parent[root] != root) {
                root = parent[root];
            }
            return root;
        }

        public void union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);
            if (rootX == rootY) {
                return;
            }

            count--;
            if (ranks[rootX] > ranks[rootY]) {
                parent[rootY] = rootX;
            } else if (ranks[rootY] > ranks[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                ranks[rootY]++;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
