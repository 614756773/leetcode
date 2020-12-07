package com.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2020/11/27
 * 思路：
 *      首先暴力肯定不可取，四重循环太吓人了，所以采用二分的思想，降低成最多两重循环
 *      首先计算出A、B数组的两数之和存在map1中，map1的key为和，value为出现的次数
 *      然后计算出C、D数组的两数之和存在map2中，map2的key为和，value为出现的次数
 *      最后遍历map1，去map2中取能够满足四数之和为0的元素，最终结果就是map1和map2 value的乘积
 */
public class 四数相加II {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0) {
            return 0;
        }
        int ret = 0;
        Map<Integer, Integer> map1 = produceMap(A, B);
        Map<Integer, Integer> map2 = produceMap(C, D);
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            ret += map2.getOrDefault(-k, 0) * v;
        }
        return ret;
    }

    /**
     * 优化，
     * 后续的两个数组其实不需要放入map了。。。直接迭代后续两个数组的之和然后去map1找对应的结果
     */
    public int fourSumCount_(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0) {
            return 0;
        }
        int ret = 0;
        Map<Integer, Integer> map1 = produceMap(A, B);
        for (int c : C) {
            for (int d : D) {
                Integer v = map1.get(-(c + d));
                if (v == null) {
                    continue;
                }
                ret += v;
            }
        }
        return ret;
    }

    private Map<Integer, Integer> produceMap(int[] array1, int[] array2) {
        int initialCapacity = (int) (array1.length / 0.75) + 1;
        Map<Integer, Integer> map = new HashMap<>(initialCapacity);
        for (int a : array1) {
            for (int b : array2) {
                int key = a + b;
                Integer v = map.getOrDefault(key, 0);
                map.put(key, v + 1);
            }
        }
        return map;
    }
}
