package com.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: qinzhu
 * @since: 2020/11/28
 */
public class 翻转对 {
    public static void main(String[] args) {
//        int[] a = new int[]{6,1,4,7,5,6,7,7,3};
//        int[] a = new int[]{2,4,3,5,1};
        int[] a = new int[]{5,6,7,7,3,9,6,1};
        System.out.println(new 翻转对().reversePairs(a));
        System.out.println(new 翻转对().reversePairs_(a));
    }

    /**
     * 思路：暴力
     */
    public int reversePairs(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        Map<Integer, Long> map = new HashMap<>((int) (nums.length / 0.75) + 1);
        for (int i = 1; i < nums.length; i++) {
            map.put(i, nums[i] * 2L);
        }
        int ret = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > map.get(j)) {
                    ret++;
                }
            }
        }
        return ret;
    }

    int res = 0;

    /**
     * 思路：分治、归并、排序
     * 假设原数组被分为a、b两个数组（a是原数组左侧的数据，b是原数组右侧的数据），并且a、b各自的翻转对已经计算出，而且a、b数组内部已经是排好序的，
     * 这时分别使用i、j两个指针来遍历a、b数组，
     * 很明显i在原数组中的索引是一直小于j在原数组的索引的（因为a数组在b数组的左侧嘛），
     * 如果a[i] > 2*b[j]，那么肯定a[i....len]都大于2*b[j]了，这就不用再计算a[i]之后的数据了
     * <p>
     * 那么现在的关键问题就是如何获得子数组a和子数组b：
     * 很简单，使用归并排序的思路，一直往下分隔数组。
     * 分割到最小单元后再归并，并且得先计算两个子数组的翻转对然后再合并排序
     */
    public int reversePairs_(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        recursion(nums, 0, nums.length - 1);
        return res;
    }

    /**
     * @param nums  数组
     * @param left  左边界元素
     * @param right 右边界元素
     */
    private void recursion(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) / 2;
        recursion(nums, left, mid);
        recursion(nums, mid + 1, right);
        mergeAndCompute(nums, left, right);
    }

    private void mergeAndCompute(int[] nums, int left, int right) {
        int mid = (left + right) / 2;

        // 先计算出翻转对
        int i = left;
        int j = mid + 1;
//        for (int j = mid + 1; j <= right; j++) {
//            for (int i = left; i <= mid; i++) {
//                if (nums[i] > 2L * nums[j]) {
//                    res += (mid - i + 1);
//                    break;
//                }
//            }
//        }
        while (i <= mid) {
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            res += j - mid - 1;
            i++;
        }

        // 然后归并排序
        i = left;
        j = mid + 1;
        int cur = 0;
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                tmp[cur++] = nums[i++];
            } else {
                tmp[cur++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[cur++] = nums[i++];
        }
        while (j <= right) {
            tmp[cur++] = nums[j++];
        }

        for (int k = left, index = 0; k <= right; k++, index++) {
            nums[k] = tmp[index];
        }
    }
}
