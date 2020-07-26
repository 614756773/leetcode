package com.medium;

/**
 * @author: qinzhu
 * @since: 2020/07/26
 * 思路：回溯
 */
public class 单词搜索 {
    private boolean result = false;
    private char[][] board;
    private char[] chs;
    private int[] dRow = new int[]{0, 0, 1, -1};
    private int[] dCol = new int[]{1, -1, 0, 0};
    private boolean[][] visited;

    public static void main(String[] args) {
        char[][] chars = new char[3][];
        chars[0] = "ABCE".toCharArray();
        chars[1] = "SFCS".toCharArray();
        chars[2] = "ADEE".toCharArray();
        System.out.println(new 单词搜索().exist(chars, "aa"));
    }

    public boolean exist(char[][] board, String word) {
        this.board = board;
        chs = word.toCharArray();
        visited = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            visited[i] = new boolean[board[i].length];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                backtrack(i, j, 0);
            }
        }
        return result;
    }

    private void backtrack(int i, int j, int charIndex) {
        if (result || board[i][j] != chs[charIndex]) {
            return;
        }

        // 搜索成功
        if (charIndex == chs.length - 1) {
            result = true;
            return;
        }

        // 四个方向搜索
        for (int k = 0; k < 4; k++) {
            int newRow = i + dRow[k];
            int newCol = j + dCol[k];
            if (newRow < 0 || newRow >= board.length ||
                    newCol < 0 || newCol >= board[i].length ||
                    visited[newRow][newCol]) {
                continue;
            }

            // 标记使用
            visited[i][j] = true;

            // 递归搜索
            backtrack(newRow, newCol, charIndex + 1);

            // 释放使用
            visited[i][j] = false;
        }
    }
}
