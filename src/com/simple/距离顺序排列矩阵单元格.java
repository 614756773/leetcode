package com.simple;

import java.util.*;

/**
 * @author qinzhu
 * @since 2020/11/17
 * 思路：
 * 方式一，把所有曼哈顿距离计算出来，然后使用排序（可以使用快速排序也可以使用桶排序）
 * 方式二，找到c0,r0点然后使用bfs一层一层的向外感染
 */
public class 距离顺序排列矩阵单元格 {
    public static void main(String[] args) {
        int[][] ints = new 距离顺序排列矩阵单元格().allCellsDistOrder_quicklySort(3, 4, 1, 3);
        System.out.println(ints);
    }
    /**
     * 方式一：使用桶排序
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 使用HashMap作为桶，key -> 曼哈顿距离,value -> 此曼哈顿距离的一系列坐标点
        Map<Integer, LinkedList<int[]>> map = new HashMap<>();
        int maxDistance = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int distance = Math.abs(i - r0) + Math.abs(j - c0);
                List<int[]> list = map.computeIfAbsent(distance, k -> new LinkedList<>());
                list.add(new int[]{i, j});
                maxDistance = Math.max(distance, maxDistance);
            }
        }

        int[][] res = new int[R * C][2];
        int cur = 0;
        for (int distance = 0; distance <= maxDistance; distance++) {
            LinkedList<int[]> queue = map.get(distance);
            while (!queue.isEmpty()) {
                res[cur++] = queue.pop();
            }
        }
        return res;
    }

    /**
     * 方式一，使用快速排序
     */
    public int[][] allCellsDistOrder_quicklySort(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][2];

        Pair[] pairs = new Pair[R * C];
        int cur = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                pairs[cur++] = new Pair(Math.abs(i - r0) + Math.abs(j - c0), new int[]{i, j});
            }
        }

        quicklySort(pairs, 0, pairs.length - 1);
        cur = 0;
        for (Pair pair : pairs) {
            ret[cur++] = pair.coordinate;
        }
        return ret;
    }

    private void quicklySort(Pair[] pairs, int low, int high) {
        if (low >= high) {
            return;
        }
        int left = low, right = high;
        Pair key = pairs[high];
        while (left < right) {
            while (left < right && pairs[left].compareTo(key) <= 0) {
                left++;
            }
            Pair tmp = pairs[left];
            pairs[left] = pairs[right];
            pairs[right] = tmp;
            while (right > left && pairs[right].compareTo(key) >= 0) {
                right--;
            }
            tmp = pairs[left];
            pairs[left] = pairs[right];
            pairs[right] = tmp;
        }
        quicklySort(pairs, low, left - 1);
        quicklySort(pairs, left + 1, high);
    }

    class Pair implements Comparable{
        int distance;
        int[] coordinate;
        public Pair(int distance, int[] coordinate) {
            this.distance = distance;
            this.coordinate = coordinate;
        }

        @Override
        public int compareTo(Object o) {
            Pair other = (Pair) o;
            return this.distance - other.distance;
        }
    }

    /**
     * 方式二，bfs + 感染
     */
    public int[][] allCellsDistOrder_bfs(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][2];
        // TODO
        return ret;
    }
}
