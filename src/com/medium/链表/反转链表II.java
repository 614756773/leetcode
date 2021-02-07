package com.medium.链表;

import com.util.ListNodeUtil;
import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2021/2/7
 */
public class 反转链表II {
    public static void main(String[] args) {
//        反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//
//        说明:
//        1 ≤ m ≤ n ≤ 链表长度。
//        输入: 1->2->3->4->5->NULL, m = 2, n = 4
//        输出: 1->4->3->2->5->NULL
        ListNode head = ListNodeUtil.produceListNodes(new Integer[]{1, 2, 3, 4, 5});
        ListNode result = new 反转链表II().reverseBetween(head, 1, 1);
        System.out.println(result);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1.找到开始的点
        ListNode originPre = null;
        ListNode cur = head;
        for (int i = 1; i < m; i++) {
            originPre = cur;
            cur = cur.next;
        }

        // 2.反转中间的点
        ListNode pre = null;
        ListNode tail = cur;
        int i = 0;
        int step = n - m;
        while (i <= step) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            i++;
        }

        // 3.将反转后的子链表和原链表对接上
        if (originPre != null) {
            // 如果从原链表第一个节点开始反转，那么originPre肯定是为null的
            originPre.next = pre;
        }
        tail.next = cur;
        // 如果m为1表示整个链表都反转了，返回pre即可。否则只是反转了中间某些节点，头结点仍然是head
        return m == 1 ? pre : head;
    }
}
