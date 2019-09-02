package com.medium;

import dataStructure.ListNode;

/**
 * @Date: 2019/8/23
 * @Author: qinzhu
 */
public class 删除链表的倒数第N个节点 {
    public static void main(String[] args) {
        删除链表的倒数第N个节点 o = new 删除链表的倒数第N个节点();
        ListNode head = new ListNode(1);
        ListNode listNode = o.removeNthFromEnd(head, 1);
        System.out.println(listNode);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null) {
            return null;
        }
        // 只有1个节点
        if (head.next == null && n == 1) {
            return null;
        }

        int nodeIndex = 1;
        // 哑结点
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        dummyNode.next = head;
        // p1:辅助指针 p2:最终指向倒数第n个节点 pre:p2的前1个节点
        ListNode p1 = dummyNode;
        ListNode p2 = dummyNode;
        ListNode pre = dummyNode;
        // dummyNode->1->2->3->4->5, 和 n = 2.
        // dummyNode->1, 2   n = 2
        while (p1.next != null && nodeIndex < n) {
            nodeIndex++;
            p1 = p1.next;
        }
        while (p1.next != null) {
            p1 = p1.next;
            pre = p2;
            p2 = p2.next;
        }

        pre.next = p2.next;
        return dummyNode.next;
    }
}

