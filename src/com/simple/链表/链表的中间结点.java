package com.simple.链表;

import datastructure.ListNode;

/**
 * @author: hotpot
 * @since: 2021/03/17
 */
public class 链表的中间结点 {
    public ListNode middleNode(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while (true) {
            if (p2.next == null) {
                return p1;
            }
            if (p2.next.next == null) {
                return p1.next;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
    }
}
