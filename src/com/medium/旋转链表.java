package com.medium;

import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2020/11/13
 * 思路： 模拟
 * 1. 设链表长度为len，要旋转k个位置，首先通过取模来简化操作步数（比如说有一个链表有3个节点，旋转2个位置和旋转5个位置结果是一样的）
 *      k = k % len
 * 2. 找到关键节点target
 *      e.g.
 *      有3个节点，要旋转2个位置也就是要把后面的2个节点挪动到前面去
 *      所以需要先找到分割点target，target位置在第1个节点，由3-2计算得。
 *      同理可得若有len个节点，要选择k个位置，target节点就是低len-k个节点
 * 3. 将target节点之后的链表移动到前面即可
 */
public class 旋转链表 {
    public static void main(String[] args) {
        ListNode root = new ListNode(0);
        root.next = new ListNode(1);
        root.next.next = new ListNode(2);
        ListNode node = new 旋转链表().rotateRight(root, 4);
        System.out.println(node);
    }

    ListNode tail = null;
    int len = 0;
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        getLenAndTail(head);
        k = k % len;
        // 这种情况下旋转后和原链表是一样的
        if (k == 0) {
            return head;
        }

        // 寻找target节点
        ListNode target = head;
        for (int i = 0; i < len - k - 1; i++) {
            target = target.next;
        }
        ListNode newHead = target.next;
        tail.next = head;
        target.next = null;
        return newHead;
    }

    private void getLenAndTail(ListNode head) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            tail = cur;
            cur = cur.next;
        }
        len = count;
    }
}
