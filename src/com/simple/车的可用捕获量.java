package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/26
 */
public class 车的可用捕获量 {
    public int numRookCaptures(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            char[] rows = board[row];
            for (int col = 0; col < rows.length; col++) {
                char c = board[row][col];
                if (c == 'R') {
                    return scanRound(board, row, col);
                }
            }
        }
        return 0;
    }

    private int scanRound(char[][] board, int row, int col) {
        int result = 0;
        // up
        for (int y = row; y > 0; y--) {
            char c = board[y][col];
            if (c == 'B') {
                break;
            }
            if (c == 'p') {
                result++;
                break;
            }
        }

        // down
        for (int y = row; y < board.length; y++) {
            char c = board[y][col];
            if (c == 'B') {
                break;
            }
            if (c == 'p') {
                result++;
                break;
            }
        }
        // left
        for (int x = col; x > 0; x--) {
            char c = board[row][x];
            if (c == 'B') {
                break;
            }
            if (c == 'p') {
                result++;
                break;
            }
        }

        // right
        for (int x = col; x < board[row].length; x++) {
            char c = board[row][x];
            if (c == 'B') {
                break;
            }
            if (c == 'p') {
                result++;
                break;
            }
        }
        return result;
    }
}
