package com.simple;

import dataStructure.ListNode;

/**
 * @Date: 2019/9/2
 * @Author: qinzhu
 */
public class 合并两个有序链表 {
    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(2);
        h1.next.next = new ListNode(4);

        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(3);
        h2.next.next = new ListNode(4);

        ListNode listNode = mergeTwoLists(h1, h2);
        System.out.println(listNode);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
                p = p.next;
            } else {
                p.next = l2;
                l2 = l2.next;
                p = p.next;
            }
        }
        listCopy(l1, p);
        listCopy(l2, p);
        return head.next;
    }

    private static void listCopy(ListNode l, ListNode p) {
        while (l != null) {
            p.next = l;
            l = l.next;
            p = p.next;
        }
    }
}
