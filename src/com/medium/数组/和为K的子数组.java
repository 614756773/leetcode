package com.medium.数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hotpot
 * @since: 2021/03/23
 * hash + 前缀和
 */
public class 和为K的子数组 {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        // key -> 前缀和的值
        // value -> 这种前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
