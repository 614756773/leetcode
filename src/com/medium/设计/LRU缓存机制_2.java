package com.medium.设计;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: hotpot
 * @since: 2021/03/19
 * 使用LinkedHashMap，效率最高
 */
public class LRU缓存机制_2 {
    private Map<Integer, Integer> map;

    public LRU缓存机制_2(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
