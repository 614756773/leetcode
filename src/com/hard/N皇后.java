package com.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hotpot
 * @since 2020-04-18 16:59:26
 * 思路：
 * 回溯，以及需要多一些的visit数组
 * 1.首先按照行来进行递归处理，这样就避免了同行出现queen
 * 2.然后使用visitedCol来标志列是否被访问，若果已访问就跳过
 * 以上就是普通的回溯，N皇后的问题还需要对角线不能出现，所以在2.中多加一个判断，如果对角线存在queen也要跳过
 */
public class N皇后 {
    private boolean[] visitedCol;
    private boolean[][] visited;
    private List<List<String>> ans = new ArrayList<>();
    private int n;

    public static void main(String[] args) {
        List<List<String>> lists = new N皇后().solveNQueens(3);
        System.out.println(lists);
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        visitedCol = new boolean[n];
        visited = new boolean[n][];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = new boolean[n];
        }
        int[] cols = new int[n];
        Arrays.fill(cols, -1);
        backtrack(0, cols);
        return ans;
    }

    /**
     * @param row  当前递归层次处理的元素是第｛row｝行
     * @param cols 存储以前的元素在哪一行哪一列
     */
    private void backtrack(int row, int[] cols) {
        if (row == n) {
            ans.add(produceGroup(cols));
        }

        for (int i = 0; i < n; i++) {
            if (visitedCol[i]) {
                continue;
            }
            if (onDiagonal(row, i)) {
                continue;
            }
            visitedCol[i] = true;
            visited[row][i] = true;
            cols[row] = i;
            backtrack(row + 1, cols);
            cols[row] = -1;
            visitedCol[i] = false;
            visited[row][i] = false;
        }
    }

    /**
     * 判断当前点是否存在某个皇后的对角线上
     */
    private boolean onDiagonal(int row, int col) {
        int[] dx = new int[]{-1, -1, 1, 1};
        int[] dy = new int[]{-1, 1, -1, 1};

        for (int i = 0; i < 4; i++) {
            Boolean x = find(row, col, dx[i], dy[i]);
            if (x) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找在对角线上是否存在有皇后
     * 通过dx和dy把整个操作分成了4个方向：
     * 左上、左下、右上、右下
     */
    private Boolean find(int row, int col, int dx, int dy) {
        // 步数，1代表对角线走1步，2就代表对角线上走2步
        int step = 1;
        int newX;
        int newY;
        while (true) {
            newX = row + step * dx;
            newY = col + step * dy;
            step++;
            if (newX < 0 || newY < 0 || newX >= n || newY >= n) {
                return false;
            }
            if (visited[newX][newY]) {
                return true;
            }
        }
    }

    private List<String> produceGroup(int[] cols) {
        List<String> group = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int queenIndex = cols[i];
            for (int j = 0; j < n; j++) {
                if (j == queenIndex) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            sb.append(",");
            sb.deleteCharAt(sb.length() - 1);
            group.add(sb.toString());
        }
        return group;
    }
}
