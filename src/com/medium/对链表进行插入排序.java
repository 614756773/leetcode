package com.medium;

import datastructure.ListNode;

/**
 * @author: qinzhu
 * @since: 2020/11/20
 * 思路：
 *      插入排序，新建一个链表。
 *      需要使用哑结点，原因在于：
 *          在遍历时需要使用pre指针记录前一个节点，
 *          由于第一个节点没有前一个节点，所以造一个dumb节点作为其前面的节点方便操作
 */
public class 对链表进行插入排序 {
    public static void main(String[] args) {
        对链表进行插入排序 qwqe = new 对链表进行插入排序();
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(2);
        a.next = b;
        ListNode c = new ListNode(1);
        b.next = c;
        ListNode d = new ListNode(3);
        c.next = d;
        ListNode listNode = qwqe.insertionSortList(a);
        System.out.println(listNode);
    }
    public ListNode insertionSortList(ListNode head) {
        ListNode dumb = new ListNode(Integer.MIN_VALUE);
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        while (cur != null) {
            ListNode nextCur = cur.next;
            ListNode preOrdered = dumb;
            ListNode ordered = dumb.next;
            while (ordered != null && cur.val > ordered.val) {
                preOrdered = ordered;
                ordered = ordered.next;
            }
            // 插入到有序链表中
            preOrdered.next = cur;
            cur.next = ordered;

            // 处理后一个待排序链表节
            cur = nextCur;
        }
        return dumb.next;
    }
}
