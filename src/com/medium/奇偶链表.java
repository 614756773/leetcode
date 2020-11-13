package com.medium;

import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2020/11/13
 * 思路：双指针  双龙戏珠
 * 使用odd、even两个指针同时迭代链表
 * 这两个指针每次都跳两个结点，分别构造出奇链表和偶链表
 * 最后再把奇链表的尾部指向偶链表的头部
 */
public class 奇偶链表 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 让链表的第一个节点作为奇节点，第二个节点作为偶节点
        ListNode odd = head, even = head.next, evenHead = even;

        while (even != null && even.next != null) {
            if (odd.next != null) {
                odd.next = odd.next.next;
                odd = odd.next;
            }
            if (even.next != null) {
                even.next = even.next.next;
                even = even.next;
            }
        }
        odd.next = evenHead;
        return head;
    }
}
