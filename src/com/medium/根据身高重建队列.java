package com.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/11/16
 * 思路：
 * 主要有两种：
 * 1. 按照h升序k降序排列，然后再处理
 * 2. 按照h降序k升序排列，然后再处理
 */
public class 根据身高重建队列 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] ints = new 根据身高重建队列().reconstructQueue_(a);
        System.out.println(ints);
    }

    private int[] NaN = new int[]{-1, -1};

    /**
     * 方式一，h升序k降序
     * 排序后，挨个把元素插入到结果数组中，
     * 插入时，元素的k是几，就把它插入到剩余位置的第几个（注意了是剩余位置的索引）
     */
    public int[][] reconstructQueue(int[][] people) {
        int[][] res = new int[people.length][2];
        if (people.length == 0) {
            return res;
        }

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        Arrays.fill(res, NaN);
        for (int[] person : people) {
            int index = findIndex(res, person[1]);
            res[index] = person;
        }
        return res;
    }

    private int findIndex(int[][] res, int k) {
        int i = 0;
        int j = -1;
        while (true) {
            if (res[i] == NaN) {
                j++;
            }
            if (j == k) {
                return i;
            }
            i++;
        }
    }

    /**
     * 方式二，h降序k升序
     * 然后利用LinkedList插入（插入的时候会把原本占据位置的元素往后移），省去手动找index的操作
     */
    public int[][] reconstructQueue_(int[][] people) {
        int[][] res = new int[people.length][2];
        if (people.length == 0) {
            return res;
        }

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });

        List<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(res);
    }
}
