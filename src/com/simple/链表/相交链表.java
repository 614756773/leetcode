package com.simple.链表;

import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2021/2/8
 * 思路：交叉  对的人终究会相遇
 */
public class 相交链表 {
    /**
     * 交叉
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return headA;
        }

        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }

    /**
     * 迭代
     */
    public ListNode getIntersectionNode_(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        while (curA != null) {
            ListNode curB = headB;
            while (curB != null) {
                if (curA == curB) {
                    return curA;
                }
                curB = curB.next;
            }
            curA = curA.next;
        }
        return curA;
    }
}
