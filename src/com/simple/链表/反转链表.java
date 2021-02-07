package com.simple.链表;

import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2021/2/7
 */
public class 反转链表 {
    public static void main(String[] args) {
//        输入: 1->2->3->4->5->NULL
//        输出: 5->4->3->2->1->NULL
    }
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
