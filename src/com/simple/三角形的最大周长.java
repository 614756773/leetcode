package com.simple;

import java.util.Arrays;

/**
 * @author: qinzhu
 * @since: 2020/11/29
 */
public class 三角形的最大周长 {
    /**
     * 思路：
     * 数学基础知识----三角形的两边之和大于第三边
     *      所以排序后，去看大的那条边的值是否小于小的两条边之和即可。
     *      若小于那么直接返回这三条边的和，若大于则找下一个数字
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i] == 0) {
                continue;
            }
            int other2sum = A[i - 1] + A[i - 2];
            if (A[i] < other2sum) {
                return A[i] + other2sum;
            }
        }

        return 0;
    }
}
