package com.simple;

/**
 * @author qinzhu
 * @since 2020/10/9
 * 思路：快慢指针
 */
public class 环形链表 {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode p1 = head, p2 = head;
        while (p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
            if (p2 == null) {
                return false;
            }
            p2 = p2.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }
}
