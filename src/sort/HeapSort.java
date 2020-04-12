package sort;

import datastructure.AbstractHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/8/20
 * @Author: qinzhu
 */
public class HeapSort extends AbstractHeap<Integer> {

    private List<Integer> list;

    @Override
    protected boolean compare(Integer e, Integer e2) {
        return e < e2;
    }

    private HeapSort(List<Integer> list) {
        this.list = list;
    }

    private List<Integer> sort() {
        list.forEach(this::add);
        List<Integer> result = new ArrayList<>();
        while (this.peek() != null) {
            result.add(this.take());
        }
        return result;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort(Arrays.asList(6, 1, 4, 7, 5, 6, 7, 7, 3));
        System.out.println(heapSort.sort());
    }
}
