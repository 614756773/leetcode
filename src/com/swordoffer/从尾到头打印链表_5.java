package com.swordoffer;

import datastructure.ListNode;

/**
 * @author: hotpot
 * @since: 2020/09/21
 */
public class 从尾到头打印链表_5 {
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        Stack stack = new Stack();
        ListNode p = head;
        int count = 0;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
            count++;
        }

        int[] res = new int[count];
        for (int i = 0; i < count; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    static class Stack {
        int[] elements = new int[16];
        int cur = -1;

        void push(int element) {
            elements[++cur] = element;
            if (cur == elements.length - 1) {
                growing();
            }
        }

        int pop() {
            if (cur == -1) {
                throw new RuntimeException("还pop个锤子，没数据了");
            }
            return elements[cur--];
        }

        private void growing() {
            int[] newElements = new int[elements.length << 1];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            this.elements = newElements;
        }
    }
}
