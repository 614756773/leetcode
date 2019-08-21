package com.medium;

import java.util.PriorityQueue;

/**
 * @Date: 2019/8/20
 * @Author: qinzhu
 */
public class 数组中的第K个最大元素 {
    public static void main(String[] args) {
        System.out.println(findKthLargest_new2(new int[]{6, 4, 1, 7}, 4));
        System.out.println(findKthLargest_new2(new int[]{3,2,3,1,2,4,5,5,6}, 1));
        System.out.println(findKthLargest_new2(new int[]{2,1}, 2));
    }

    /**
     * 手动实现大顶堆
     */
    private static int findKthLargest_new2(int[] nums, int k) {
        // 1.入堆
        int[] heap = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            heap[i] = num;
            int currentIndex = i;
            int parentIndex = (currentIndex - 1) >> 1;
            while (parentIndex >= 0 &&
                    currentIndex >= 0 &&
                     heap[currentIndex] > heap[parentIndex]) {
                int swap = heap[currentIndex];
                heap[currentIndex] = heap[parentIndex];
                heap[parentIndex] = swap;
                currentIndex = parentIndex;
                parentIndex = (currentIndex - 1) >> 1;
            }
        }
        // 2.出堆
        // 堆最后一个元素的索引
        int heapTail = heap.length - 1;
        for (int i = 0; ; i++) {
            if (i == k - 1) {
                return heap[0];
            }

            heap[0] = heap[heapTail--];
            int currentIndex = 0;
            int left = (currentIndex << 1) + 1;
            if (left > heapTail) {
                continue;
            }
            int right = left + 1;
            int childIndex = heap[left] > heap[right] ? left : right;
            while (heap[currentIndex] < heap[childIndex]) {
                int swap = heap[currentIndex];
                heap[currentIndex] = heap[childIndex];
                heap[childIndex] = swap;
                currentIndex = childIndex;
                left = (currentIndex << 1) + 1;
                if (left > heapTail) {
                    break;
                }
                right = left + 1;
                childIndex = right > heapTail ? left : heap[left] > heap[right] ? left : right;
            }
        }
    }

    /**
     * 使用大顶堆实现
     */
    private static int findKthLargest_new1(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            heap.add(num);
        }
        for (int i = 1; i < k; i++) {
            heap.poll();
        }
        return heap.poll();
    }

    /**
     * 使用选择法+双向链表
     * 当要找第k大的数时，就找k轮最大的数，每一轮都把最大的那个数字从队列中移除
     */
    public static int findKthLargest_old(int[] nums, int k) {
        Queue head = init(nums);
        for (int i = 0; ; ) {
            Queue p = head.next;
            int max = Integer.MIN_VALUE;
            Queue maxNode = null;
            while (p != null) {
                if (p.data > max) {
                    max = p.data;
                    maxNode = p;
                }
                p = p.next;
            }
            i++;
            if (i == k) {
                return max;
            }
            // 从队列中移除最大的元素
            // 1.最大的元素恰好是第一个元素
            if (head.next == maxNode) {
                head.next.next.pre = head;
                head.next = head.next.next;
            } else if (maxNode.next == null) {
                // 2.最大的元素是最后一个元素
                maxNode.pre.next = null;
                maxNode.pre = null;
            } else {
                // 3.最大的元素在中间
                maxNode.pre.next = maxNode.next;
                maxNode.next.pre = maxNode.pre;
            }
        }
    }

    private static Queue init(int[] nums) {
        Queue p = new Queue();
        Queue head = p;
        for (int num : nums) {
            Queue q = new Queue();
            q.data = num;
            p.next = q;
            q.pre = p;
            p = q;
        }
        return head;
    }

    static class Queue {
        int data;
        Queue next;
        Queue pre;
    }
}
