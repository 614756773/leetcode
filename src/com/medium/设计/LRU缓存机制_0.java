package com.medium.设计;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: hotpot
 * @since: 2021/03/19
 * 使用LinkedList和HashMap，效率比较低。
 * 主要是在移除节点时时间复杂度为O(n)
 */
public class LRU缓存机制_0 {
    private int capacity;
    private LinkedList<Integer> keyList = new LinkedList<>();
    private Map<Integer, Integer> map = new HashMap<>();

    public LRU缓存机制_0(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = map.get(key);
        if (value == null) {
            return -1;
        }
        remove(key);
        keyList.addLast(key);
        return value;
    }

    public void put(int key, int value) {
        // 1.原来就有这个key
        if (map.containsKey(key)) {
            remove(key);
        }
        // 2.容量已满
        if (keyList.size() >= capacity) {
            Integer oldKey = keyList.removeFirst();
            map.remove(oldKey);
        }
        keyList.addLast(key);
        map.put(key, value);
    }

    private void remove(int key) {
        Iterator<Integer> iterator = keyList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(key)) {
                iterator.remove();
                break;
            }
        }
    }
}
