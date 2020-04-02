package com.medium;

public class 生命游戏 {
    private int[] dx = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    private int[] dy = new int[]{0, 0, 1, -1, 1, -1, 1, -1};

    public static void main(String[] args) {
        int[][] a = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        new 生命游戏().gameOfLife(a);
    }
    public void gameOfLife(int[][] board) {
        int[][] result = new int[board.length][];

        for (int i = 0; i < board.length; i++) {
            int len = board[i].length;
            result[i] = new int[len];
            for (int j = 0; j < len; j++) {
                int value = board[i][j];
                if (value == 0) {
                    result[i][j] = revive(i, j, board) ? 1 : 0;
                } else {
                    result[i][j] = die(i, j, board) ? 0 : 1;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(result[i], 0, board[i], 0, board[i].length);
        }
    }

    /**
     * 死亡
     */
    private boolean die(int i, int j, int[][] board) {
        int liveCount = 0;
        for (int k = 0; k < 8; k++) {
            int newX = i + dx[k];
            if (newX < 0 || newX >= board.length) {
                continue;
            }
            int newY = j + dy[k];
            if (newY < 0 || newY >= board[newX].length) {
                continue;
            }
            if (board[newX][newY] == 1) {
                liveCount++;
            }
            if (liveCount > 3) {
                return true;
            }
        }
        return liveCount < 2;
    }

    /**
     * 是否能够复活
     */
    private boolean revive(int i, int j, int[][] board) {
        int liveCount = 0;
        for (int k = 0; k < 8; k++) {
            int newX = i + dx[k];
            if (newX < 0 || newX >= board.length) {
                continue;
            }
            int newY = j + dy[k];
            if (newY < 0 || newY >= board[newX].length) {
                continue;
            }
            if (board[newX][newY] == 1) {
                liveCount++;
            }
        }
        return liveCount == 3;
    }
}
