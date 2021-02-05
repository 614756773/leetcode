package com.simple.链表;

import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2021/2/5
 * 思路：迭代 双指针
 * 使用cur记录当前节点，nextNode为我们要找的下一个节点，使用temp记录当前节点的值。
 * 只要nextNode的值等于当前值，那就是重复的，
 * 所以nextNode继续往后移动，最后将cur.next指向nextNode，
 * 然后还需要更新cur = cur.next;temp = cur.val;
 */
public class 删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        int temp = head.val;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            while (nextNode != null && nextNode.val == temp) {
                nextNode = nextNode.next;
            }
            if (nextNode != null) {
                temp = nextNode.val;
            }
            cur.next = nextNode;
            cur = cur.next;
        }
        return head;
    }
}
