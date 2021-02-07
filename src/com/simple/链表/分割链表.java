package com.simple.链表;

import com.util.ListNodeUtil;
import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2021/2/7
 * 思路：拆分链表  合并链表
 * 根据x的值，将原链表拆分成small链表和large链表，
 * 然后再把small、large拼接起来（x的节点直接被放在large链表中）
 */
public class 分割链表 {
    public static void main(String[] args) {
//        输入：head = 1->4->3->2->5->2, x = 3
//        输出：1->2->2->4->3->5
        ListNode head = ListNodeUtil.produceListNodes(new Integer[]{1, 4, 3, 2, 5, 2});
        ListNode result = new 分割链表().partition(head, 3);
        System.out.println(result);
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = new ListNode(0), large = new ListNode(0);
        ListNode smallTail = small, largeTail = large;
        // 1.区分small和large链表
        while (head != null) {
            if (head.val < x) {
                smallTail.next = head;
                smallTail = smallTail.next;
            } else {
                largeTail.next = head;
                largeTail = largeTail.next;
            }
            head = head.next;
        }

        // 2.合并small、large链表
        smallTail.next = large.next;
        largeTail.next = null;

        return small.next;
    }

}
