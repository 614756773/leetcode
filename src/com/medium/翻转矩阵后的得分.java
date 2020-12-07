package com.medium;

/**
 * @author: qinzhu
 * @since: 2020/12/07
 * 思路：贪心
 * 首先，高位的数字越大肯定最后的值就越大，那么就将所有行的第一位数字变为1（通过转换行或者转换列完成，反正是肯定能实现的）
 * 然后再按列转换，保证每列有尽可能多的1
 */
public class 翻转矩阵后的得分 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}};
//        int[][] a = new int[][]{{0,1},{1,1}};
        System.out.println(new 翻转矩阵后的得分().matrixScore(a));
    }

    public int matrixScore(int[][] A) {
        // 将第一列全都转换为1
        setFirstCol(A);
        // 将其余列设置为1尽可能的多
        setOtherCol(A);
        // 计算结果
        return compute(A);
    }

    private int compute(int[][] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            int[] row = a[i];
            for (int j = row.length - 1; j >= 0; j--) {
                int v = row[j];
                if (v == 0) {
                    continue;
                }
                int shift = row.length - j - 1;
                sum += (1 << shift);
            }
        }
        return sum;
    }

    private void setOtherCol(int[][] a) {
        int rowNum = a.length;
        int colNum = a[0].length;
        for (int i = 1; i < colNum; i++) {
            // 1的数量
            int count_1 = 0;
            for (int j = 0; j < rowNum; j++) {
                if (a[j][i] == 1) {
                    count_1++;
                }
            }
            // 如果该列1的数量比较少，则翻转
            int mid = (rowNum + 1) / 2;
            if (count_1 < mid) {
                for (int j = 0; j < rowNum; j++) {
                    a[j][i] = (a[j][i] ^ 1);
                }
            }
        }
    }

    private void setFirstCol(int[][] a) {
        for (int[] row : a) {
            if (row[0] == 1) {
                continue;
            }
            for (int j = 0; j < row.length; j++) {
                row[j] = (row[j] ^ 1);
            }
        }
    }
}
