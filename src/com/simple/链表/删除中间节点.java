package com.simple.链表;

import datastructure.ListNode;

/**
 * @author: qinzhu
 * @since: 2021/02/01
 */
public class 删除中间节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
