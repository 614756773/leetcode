package com.medium.链表;

import com.util.ListNodeUtil;
import datastructure.ListNode;

import java.util.LinkedList;

/**
 * @author qinzhu
 * @since 2021/2/8
 * 思路： 使用数组或者双向队列，然后首位取数
 */
public class 重排链表 {
    public static void main(String[] args) {
//        示例 1:
//
//        给定链表 1->2->3->4, 重新排列为 1->4->2->3.
//        示例 2:
//
//        给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
        ListNode head = ListNodeUtil.produceListNodes(new Integer[]{1,2,3});
        new 重排链表().reorderList(head);
        System.out.println(head);
    }

    public void reorderList(ListNode head) {
        // 0、1、2个节点直接返回原链表
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        LinkedList<ListNode> queue = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            queue.addLast(cur);
            cur = cur.next;
        }

        ListNode temp = new ListNode(-1);
        boolean flag = true;
        while (!queue.isEmpty()) {
            ListNode node;
            if (flag) {
                node = queue.removeFirst();
            } else {
                node = queue.removeLast();
            }
            flag = !flag;
            temp.next = node;
            temp = temp.next;
        }
        // 要把最后一个节点的next设置为null，否则有循环链表
        temp.next = null;
    }


}
