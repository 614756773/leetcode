package com.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/12/8
 * 思路： 模拟
 */
public class 杨辉三角 {
    public static void main(String[] args) {
        int a = -9;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a >>> 3));
        System.out.println(a >>> 3);
    }

    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (numRows < 1) {
            return result;
        }

        result.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            Integer[] tmp = new Integer[i + 1];
            tmp[0] = 1;
            int len = tmp.length;
            tmp[len - 1] = 1;
            // 上一行
            List<Integer> preRow = result.get(i - 1);
            for (int j = 1; j < len - 1; j++) {
                tmp[j] = preRow.get(j - 1) + preRow.get(j);
            }
            result.add(Arrays.asList(tmp));
        }
        return result;
    }
}
