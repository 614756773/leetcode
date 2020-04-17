package com.medium;

import java.util.Arrays;

/**
 * @author qinzhu
 * @since 2020/4/17
 * 思路：
 * 先对输入按照每个区间的左边值进行升序排序，排列好后如：
 * {1, 3}, {2, 6}, {8, 10}, {15, 18},{-1,7}
 * 假设a为合并区间，b为待合并区间
 * 当b的左边值 <= a的右边值时，说明能够将两个区间进行合并
 *      继续判断下面两者情况：
 *          当b的右边值 > a的右边值时，说明会发生区间扩大
 *          当b的右边值 < a的右边值时，说明是直接把b并入a中
 */
public class 合并区间 {
    public static void main(String[] args) {
//        int[][] a = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18},{-1,7}};
        int[][] a = new int[][]{{1, 3}, {3, 5}};
        int[][] merge = new 合并区间().merge(a);
        System.out.println(merge);
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        int[][] ans = new int[intervals.length][];
        int count = 0;

        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));
        ans[0] = new int[2];
        ans[0][0] = intervals[0][0];
        ans[0][1] = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int preEnd = ans[count][1];
            int curBegin = intervals[i][0];
            if (curBegin <= preEnd) {
                int curEnd = intervals[i][1];
                if (curEnd > preEnd) {
                    // 合并，并且扩大原区间
                    ans[count][1] = curEnd;
                }
            } else {
                count++;
                ans[count] = new int[2];
                ans[count][0] = intervals[i][0];
                ans[count][1] = intervals[i][1];
            }
        }

        return Arrays.copyOf(ans, count + 1);
    }
}
