package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/23
 */
public class 链表的中间结点 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        head.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        ListNode middleNode = new 链表的中间结点().middleNode(head);
        System.out.println(middleNode.val);
    }

    /**
     * 也可以使用快慢指针
     */
    public ListNode middleNode(ListNode head) {
        ListNode[] table = new ListNode[100];
        int index = 0;

        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }

        ListNode p = head;
        while (p != null) {
            table[index++] = p;
            p = p.next;
        }

        int middle = index / 2;
        return table[middle];
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
