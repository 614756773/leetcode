package com.simple;

/**
 * @author qinzhu
 * @since 2020/11/12
 * 思路：遍历原数组，使用双指针将数据存放在结果集中
 */
public class 按奇偶排序数组II {
    public int[] sortArrayByParityII(int[] A) {
        if (A.length == 0) {
            return A;
        }

        int[] res = new int[A.length];
        for (int i = 0, odd = 1, even = 0; i < A.length; i++) {
            int e = A[i];
            if ((e & 1) == 0) {
                res[even] = e;
                even += 2;
            } else {
                res[odd] = e;
                odd += 2;
            }
        }
        return res;
    }
}
