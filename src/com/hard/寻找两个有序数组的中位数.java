package com.hard;

import java.io.IOException;

/**
 * @Date: 2019/6/10
 * @Author: qinzhu
 */
public class 寻找两个有序数组的中位数 {

    private static final double NO_EMPTY_ARRAY = -1.1111111111111;

    public static void main(String[] args) throws IOException {
        int[] nums1 = new int[]{1, 9, 20, 63, 90, 888, 1206};
        int[] nums2 = new int[]{1, 3, 60, 80, 99, 165, 856, 1879};
        double v = findMedianSortedArrays(nums1, nums2);
        System.out.println(v);
    }


    /**
     * 二分查找 分治算法
     */
    private static double findMedianSortedArrays(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        // 确保m<=n,通过交换保证
        if (m > n) {
            int[] temp = a;
            a = b;
            b = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && b[j - 1] > a[i]) {
                // i is too small
                iMin = i + 1;
            } else if (i > iMin && a[i - 1] > b[j]) {
                // i is too big
                iMax = i - 1;
            } else { // i is perfect
                int maxLeft;
                if (i == 0) {
                    maxLeft = b[j - 1];
                } else if (j == 0) {
                    maxLeft = a[i - 1];
                } else {
                    maxLeft = Math.max(a[i - 1], b[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight;
                if (i == m) {
                    minRight = b[j];
                } else if (j == n) {
                    minRight = a[i];
                } else {
                    minRight = Math.min(b[j], a[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    /**
     * 原创，归并两个数组，取中间
     */
    private static double findMedianSortedArrays_ugly(int[] nums1, int[] nums2) {
        double check = check(nums1, nums2);
        if (check != NO_EMPTY_ARRAY) {
            return check;
        }

        int[] tmp = new int[nums1.length + nums2.length];
        int k = 0, i = 0, j = 0;
        while (true) {
            if (nums1[i] < nums2[j]) {
                tmp[k++] = nums1[i++];
            } else {
                tmp[k++] = nums2[j++];
            }
            if (k == tmp.length || i == nums1.length || j == nums2.length) {
                break;
            }
        }
        while (i < nums1.length) {
            tmp[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            tmp[k++] = nums2[j++];
        }

        return midNum(tmp);
    }

    private static double check(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return midNum(nums2);
        }
        if (nums2 == null || nums2.length == 0) {
            return midNum(nums1);
        }
        return NO_EMPTY_ARRAY;
    }


    /**
     * 取有序数组的中位数
     *
     * @param tmp 有序数组
     */
    private static double midNum(int[] tmp) {
        if (tmp.length % 2 != 0) {
            return tmp[tmp.length / 2];
        }
        double d = (double) ((tmp[tmp.length / 2 - 1]) + tmp[tmp.length / 2]);
        return d / 2;
    }
}
