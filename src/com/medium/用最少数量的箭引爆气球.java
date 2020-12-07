package com.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author qinzhu
 * @since 2020/11/23
 * 思路： 贪心
 * 首先根据所有气球的右边界值进行排序，选择右边界最靠左的那个气球，
 * 然后让箭从该气球的右边界射出去，此时就是在穿过这个气球的情况下所能穿透最多气球的解（此处就是`贪心`的过程），
 * 把上面已经被穿透过的气球排除，接着用同样的操作处理剩下的气球
 */
public class 用最少数量的箭引爆气球 {
    public static void main(String[] args) {
        System.out.println(new 用最少数量的箭引爆气球().findMinArrowShots_(new int[][]{{10, 16}, {2, 8},{1,6},{7,12}}));
    }

    public int findMinArrowShots(int[][] points) {
        int ret = 0;
        Arrays.sort(points, (o1, o2) -> {
            int a = o1[1];
            int b = o2[1];
            // 此处不能返回a - b，因为可能溢出
            return Integer.compare(a, b);
        });

        // 使用hash来记录已经射爆过的气球
        int[] hash = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            if (hash[i] != 0) {
                continue;
            }

            // 射
            ret++;
            hash[i] = 1;
            int x = points[i][1];
            // 判断有哪些气球是会被同时射爆的
            for (int j = i + 1; j < points.length; j++) {
                if (hash[j] != 0) {
                    continue;
                }
                // 按道理说应该判断x落在区间内，即points[j][0] <= x && x <= points[j][1]
                // 但是因为已经拍过序了，直接判断新区间的左侧是否在x左边就行了，画图就能看明白
                if (points[j][0] <= x) {
                    hash[j] = 1;
                }
            }
        }
        return ret;
    }


    /**
     * 优化：
     *      主要思路和上一版的代码是一样的，都是先根据右边界值排序，然后用当前区间的右边界值作为参考点去比较下一个区间的左边界值
     *      优化的地方在于：
     *          如果下一个区间的左边界比当前区间的右边界值大了，其实就可以跳出这个内循环了，不必在比较后面的区间，
     *          直接使用下一个区间的右边界值作为新的参考点然后继续操作。在接下来的操作中肯定会穿透尽量多的气球。
     *          这种做法会导致穿透气球的方案不同，但是需要穿透的次数和findMinArrowShots（）计算出来的结果是一样的
     */
    public int findMinArrowShots_(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        int ret = 1;
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));

        int lastRight = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > lastRight) {
                lastRight = points[i][1];
                ret++;
            }
        }
        return ret;
    }
}
