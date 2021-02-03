package com.medium.链表;

import datastructure.ListNode;
import datastructure.Node;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: qinzhu
 * @since: 2021/02/01
 */
public class 两两交换链表中的节点 {
    /**
     * 思路：迭代 三指针 哑结点
     * https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/
     *
     * 使用dummy节点，初始时dummy.next指向head节点。
     * 然后使用三个指针temp、node1、node2，
     * temp最开始为dummy节点，然后开始算法：
     * temp.next指向node2，
     * node1.next指向node2.next，
     * node2.next指向node1，
     * （以上就完成了一次两个节点的交换）
     * 然后将temp指向原来的node1节点，开始下一个循环（直到temp后面的节点只有1个或者0个）
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy, node1, node2;
        while (temp.next != null && temp.next.next != null) {
            node1 = temp.next;
            node2 = node1.next;

            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            temp = node1;
        }
        return dummy.next;
    }

    /**
     * 思路：递归
     */
    int level = 0;
    public ListNode swapPairs_recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        print(head);
        level++;
        head.next = swapPairs_recursion(newHead.next);
        level--;
        print(head);
        newHead.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        两两交换链表中的节点 o = new 两两交换链表中的节点();
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        o.swapPairs_recursion(head);
    }

    private void print(ListNode head) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println("level:" + level + ",value:" + head.val);
    }
}
