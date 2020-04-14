package com.medium;

import datastructure.ListNode;

import java.util.LinkedList;

/**
 * @author qinzhu
 * @since 2020/4/14
 * 思路：
 * 方法一：先把两条链表补齐，然后递归处理
 * 方法二：使用栈以及头插法（当前使用的就是方法二）
 */
public class 两数相加II {
    private int addition = 0;
    
    private ListNode head = null;
    
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(9);
        b.next = new ListNode(9);
        ListNode ans = new 两数相加II().addTwoNumbers(a, b);
        System.out.println(ans);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 入栈
        LinkedList<Integer> stackA = new LinkedList<>();
        LinkedList<Integer> stackB = new LinkedList<>();
        ListNode p = l1;
        while (p != null) {
            stackA.push(p.val);
            p = p.next;
        }
        p = l2;
        while (p != null) {
            stackB.push(p.val);
            p = p.next;
        }

        // 出栈，头插法生成结果链表
        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            int i = stackA.pop() + stackB.pop() + addition;
            insert(i);
        }

        // 余下的数使用完
        while (!stackA.isEmpty()) {
            int i1 = stackA.pop() + addition;
            insert(i1);
        }
        while (!stackB.isEmpty()) {
            int i = stackB.pop() + addition;
            insert(i);
        }

        // 特殊情况处理：最高位还要进位，
        // 比如5和5,会生成新的链表1 -> 0
        if (addition > 0) {
            ListNode node = new ListNode(addition);
            node.next = head;
            head = node;
        }
        return head;
    }

    private void insert(int value) {
        if (value >= 10) {
            addition = 1;
            value = value - 10;
        } else {
            addition = 0;
        }

        ListNode currentNode = new ListNode(value);
        if (head == null) {
            head = currentNode;
        } else {
            currentNode.next = head;
            head = currentNode;
        }
    }
}
