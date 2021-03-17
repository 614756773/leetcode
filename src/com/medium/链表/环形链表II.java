package com.medium.链表;

import datastructure.ListNode;

import java.util.LinkedHashMap;

/**
 * @author: hotpot
 * @since: 2021/03/17
 */
public class 环形链表II {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(10, 0.75f, true);
    }
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
