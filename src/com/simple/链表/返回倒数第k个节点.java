package com.simple.链表;

import datastructure.ListNode;

/**
 * @author: qinzhu
 * @since: 2021/02/01
 */
public class 返回倒数第k个节点 {
    public int kthToLast(ListNode head, int k) {
        if (head == null) {
            return -1;
        }
        ListNode p1 = head;
        for (int i = 0; i < k - 1; i++) {
            p1 = p1.next;
        }

        ListNode p2 = head;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2.val;
    }
}
