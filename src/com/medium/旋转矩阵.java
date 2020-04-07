package com.medium;

/**
 * 思路：先以左上右下为轴进行翻转，再将每行以中轴进行翻转
 */
public class 旋转矩阵 {
    public static void main(String[] args) {
        int[][] a = new int[3][];
        a[0] = new int[]{1,2,3};
        a[1] = new int[]{4,5,6};
        a[2] = new int[]{7,8,9};

        new 旋转矩阵().rotate(a);
    }
    public void rotate(int[][] matrix) {
        int tmp;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - j - 1];
                matrix[i][matrix.length - j - 1] = tmp;
            }
        }
    }
}
