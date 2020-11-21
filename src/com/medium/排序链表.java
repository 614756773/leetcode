package com.medium;

import datastructure.ListNode;

/**
 * @author: qinzhu
 * @since: 2020/11/21
 * 思路： 归并
 */
public class 排序链表 {
    /**
     * 递归方式的归并
     * 使用快慢指针找到中间节点（如果是奇数个节点肯定用中央的就行，如果是偶数个节点则使用偏左侧的），然后进行断链操作
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }

        return parting(head);
    }

    private ListNode parting(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时slow就是中间节点，所以进行断链处理。然后递归处理[head,slow]以及[slow.next,tail]
        ListNode otherHead = slow.next;
        slow.next = null;
        head = parting(head);
        otherHead = parting(otherHead);

        // merge操作，使用dumb节点简化操作
        ListNode dumb = new ListNode(-1);
        ListNode cur = dumb;
        while (head != null && otherHead != null) {
            if (head.val < otherHead.val) {
                cur.next = head;
                head = head.next;
            } else {
                cur.next = otherHead;
                otherHead = otherHead.next;
            }
            cur = cur.next;
        }
        cur.next = head == null ? otherHead : head;
        return dumb.next;
    }
}
