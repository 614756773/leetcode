package com.medium.设计;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hotpot
 * @since: 2021/03/19
 * 使用链表和HashMap，效率不错，因为在移除节点时时间复杂度已经优化到O(1)了。
 * 链表是自己实现的一个双向链表，带有头尾两个哨兵节点，同时内部有一个Map，用于提升remove效率。
 */
public class LRU缓存机制_1 {
    public static void main(String[] args) {
        LRU缓存机制_1 o = new LRU缓存机制_1(2);
        o.put(1,1);
        o.put(2,2);
        o.get(1);
        o.put(3,3);
        o.get(2);
        o.put(4,4);
        o.get(1);
        o.get(3);
        o.get(4);
    }
    private int capacity;
    private MyLinkedList<Integer> keyList = new MyLinkedList<>();
    private Map<Integer, Integer> map = new HashMap<>();

    public LRU缓存机制_1(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = map.get(key);
        if (value == null) {
            return -1;
        }
        keyList.remove(key);
        keyList.addLast(key);
        return value;
    }

    public void put(int key, int value) {
        // 1.原来就有这个key
        if (map.containsKey(key)) {
            keyList.remove(key);
        }
        // 2.容量已满
        if (keyList.size >= capacity) {
            Integer oldKey = keyList.removeFirst();
            map.remove(oldKey);
        }
        keyList.addLast(key);
        map.put(key, value);
    }

    static class MyLinkedList<T> {
        private Node<T> dummyHead = new Node<>(null);
        private Node<T> dummyTail = new Node<>(null);
        // 使用此map加快remove操作
        private Map<T, Node<T>> map = new HashMap<>();
        private int size = 0;

        public void addLast(T data) {
            size++;
            Node<T> node = new Node<>(data);
            map.put(data, node);
            if (dummyTail.pre == null) {
                dummyHead.next = node;
                node.pre = dummyHead;
                dummyTail.pre = node;
                node.next = dummyTail;
                return;
            }

            Node<T> tail = dummyTail.pre;
            tail.next = node;
            node.pre = tail;
            node.next = dummyTail;
            dummyTail.pre = node;
        }

        public T removeFirst() {
            if (size == 0) {
                return null;
            }
            size--;
            Node<T> head = dummyHead.next;
            Node<T> next = head.next;
            dummyHead.next = next;
            next.pre = dummyHead;
            if (size == 0) {
                dummyTail.next = null;
                dummyTail.pre = null;
            }
            return head.value;
        }

        public void remove(T data) {
            Node<T> node = map.get(data);
            if (node == null) {
                return;
            }
            size--;
            Node<T> pre = node.pre;
            Node<T> next = node.next;
            pre.next = next;
            next.pre = pre;
            if (size == 0) {
                dummyTail.next = null;
                dummyTail.pre = null;
            }
        }
    }

    static class Node<T> {
        private T value;
        private Node<T> pre;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
