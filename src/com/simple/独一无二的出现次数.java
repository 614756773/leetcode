package com.simple;

/**
 * @author qinzhu
 * @since 2020/10/28
 * 思路：hash
 */
public class 独一无二的出现次数 {
    public static void main(String[] args) {
        uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3});
    }

    public static boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        // hash[0]到hash[1000]存放元素[0,1,2....1000]
        // hash[1001]存放-1，hash[1002]存放-2，hash[2000]存放-1000
        int[] hash = new int[2001];
        for (int num : arr) {
            if (num >= 0) {
                hash[num]++;
            } else {
                int tmp = -num + 1000;
                hash[tmp]++;
            }
        }

        int[] countHash = new int[1001];
        for (int count : hash) {
            // count为0意味着出现过零次的数字，没有必要计算
            if (count == 0) {
                continue;
            }

            if (countHash[count] != 0) {
                return false;
            }
            countHash[count] = 1;
        }
        return true;
    }
}
