package com.util;

import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2021/2/5
 */
public class ListNodeUtil {
    /**
     * 根据数组构造链表，并且返回头结点
     */
    public static ListNode produceListNodes(Integer[] vals) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (Integer v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }
}
