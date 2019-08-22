package com.medium;

import java.util.Iterator;
import java.util.List;

/**
 * @Date: 2019/8/22
 * @Author: qinzhu
 */
public class 顶端迭代器 {
}
class PeekingIterator implements Iterator<Integer> {

    private int size = 8;
    private Integer[] elements = new Integer[size];
    // p指向当前元素
    private int p = 0;
    // q指向最后一个元素的下一个索引
    private int q = p;

    public PeekingIterator(Iterator<Integer> iterator) {
        while (iterator.hasNext()) {
            if (q == size) {
                grow();
            }
            elements[q++] = iterator.next();
        }
    }

    public Integer peek() {
        return elements[p];
    }

    @Override
    public Integer next() {
        return elements[p++];
    }

    @Override
    public boolean hasNext() {
        return p != q;
    }

    private void grow() {
        int newSize = size > Integer.MAX_VALUE >> 1 ? Integer.MAX_VALUE : size << 1;
        Integer[] objects = new Integer[newSize];
        System.arraycopy(elements, 0, objects, 0 , size);
        size = newSize;
        elements = objects;
    }
}
