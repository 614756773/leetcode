package com.simple;

import java.util.*;

/**
 * @author qinzhu
 * @since 2020/11/6
 */
public class 根据数字二进制下1的数目排序 {
    // TODO
    public static void main(String[] args) {
        System.out.println(new 根据数字二进制下1的数目排序().compare(4,8));
//        int[] num = {3, 1, 2, 4};
//        test(num);
//        test(null);
//        test(new int[]{});
//        test(new int[]{6,1,4,7,5,6,7,7,3});
//        test(new int[]{1,2,3,4,5,6,7,8,9});
//        test(new int[]{9,8,7,6,5,4,3,2,1});
        test(new int[]{0,1,2,3,4,5,6,7,8});

    }

    private static void test(int[] num) {
        System.out.println(Arrays.toString(new 根据数字二进制下1的数目排序().sortByBits(num)));
    }

    public int[] sortByBits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>(arr.length);
        for (int a : arr) {
            list.add(a);
        }
        list.sort(this::compare);
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
//        quicklySort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quicklySort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int key = arr[high];
        int left = low, right = high;
        while (low < high) {
            // arr[low] <= key && low < high
            while (compare(arr[low], key) <= 0 && low < high) {
                low++;
            }
            // arr[low] > key
            if (low < high) {
                int tmp = arr[low];
                arr[low] = arr[high];
                arr[high] = tmp;
            }
            // arr[high] >= key && high > low
            while (compare(arr[high], key) >= 0 && high > low) {
                high--;
            }
            // arr[high] < key
            if (low < high) {
                int tmp = arr[low];
                arr[low] = arr[high];
                arr[high] = tmp;
            }
        }
        quicklySort(arr, left, low - 1);
        quicklySort(arr, low + 1, right);
    }

    /**
     * 根据题目要求比较两个数字的大小
     *
     * @return -1表示a < b    0表示a = b    1表示a > b
     */
    private int compare(int a, int b) {
        if (a == b) {
            return 0;
        }
        int aBitCount = computeBitCount(a);
        int bBitCount = computeBitCount(b);
        if (aBitCount > bBitCount) {
            return 1;
        } else if (aBitCount < bBitCount) {
            return -1;
        } else {
            return a > b ? 1 : 0;
        }
    }

    private int computeBitCount(int a) {
        int cur = 0;
        int res = 0;
        while (true) {
            int mask = 1 << cur;
            if (mask > a) {
                break;
            }
            if ((a & mask) == mask) {
                res++;
            }
            cur++;
        }
        return res;
    }
}
