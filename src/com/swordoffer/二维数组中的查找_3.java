package com.swordoffer;

/**
 * @author: hotpot
 * @since: 2020/09/21
 * 思路：从右往左找，从上往下找。（有点跳表的感觉，向前还是向下）
 */
public class 二维数组中的查找_3 {
    /**
     * @param matrix  二维数组
     * @param target  查找的数字
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int row = 0, col = columns - 1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}
