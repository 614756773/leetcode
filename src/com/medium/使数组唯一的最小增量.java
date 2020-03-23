package com.medium;

import java.util.Arrays;

/**
 * @author qinzhu
 * @since 2020/3/23
 */
public class 使数组唯一的最小增量 {
    public static void main(String[] args) {
        System.out.println(new 使数组唯一的最小增量().minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7}));
    }

    public int minIncrementForUnique(int[] a) {
        if (a.length == 1) {
            return 0;
        }
        if (a.length == 2) {
            return a[0] == a[1] ? 1 : 0;
        }
        Arrays.sort(a);
        int result = 0;
        for (int i = 1; i < a.length; i++) {
            int current = a[i];
            int before = a[i - 1];
            if (current <= before) {
                int move = before - current + 1;
                result += move;
                a[i] += move;
            }
        }
        return result;
    }
}
