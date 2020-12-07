package com.medium;

import java.util.LinkedList;

/**
 * @author qinzhu
 * @since 2020/11/18
 * 思路：
 *  方法一：暴力
 *      1. 先计算出每个对应的gas、cost的差值，将结果存储在diff数组中
 *      2. 若要能走完一圈，肯定起点得在diff为正数的位置。根据此条件找到各个起点
 *      3. 根据起点遍历diff，若在途中与diff想加的值为负数则说明不能走完一圈，否则可以
 *
 */
public class 加油站 {
    public static void main(String[] args) {
        int i = new 加油站().canCompleteCircuit(new int[]{2, 0, 1, 2, 3, 4, 0}, new int[]{0, 1, 0, 0, 0, 0, 11});
        System.out.println(i);
    }

    /**
     * 暴力法
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0) {
            return 0;
        }
        int ret = -1;
        int[] diff = new int[gas.length];

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < gas.length; i++) {
            diff[i] = gas[i] - cost[i];
            if (diff[i] >= 0) {
                queue.addLast(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer index = queue.removeFirst();
            int remaining = diff[index];
            boolean failEarly = false;
            // 从起点往最后一个点走
            for (int i = index + 1; i < gas.length; i++) {
                remaining += diff[i];
                if (remaining < 0) {
                    failEarly = true;
                    break;
                }
            }

            if (failEarly) {
                continue;
            }

            // 从第0个点开始走，直到回到起点位置
            for (int i = 0; i < index; i++) {
                remaining += diff[i];
                if (remaining < 0) {
                    break;
                }
            }
            if (remaining >= 0) {
                return index;
            }
        }
        return ret;
    }
}
