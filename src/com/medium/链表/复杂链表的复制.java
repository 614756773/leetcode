package com.medium.链表;


/**
 * @author: qinzhu
 * @since: 2021/02/04
 * 思路：原地操作  复制链表  拆分链表
 */
public class 复杂链表的复制 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 1.在原链表中复制节点
        Node temp = head;
        while (temp != null) {
            Node copyNode = new Node(temp.val);
            copyNode.next = temp.next;
            temp.next = copyNode;
            temp = copyNode.next;
        }
        // 2.设置copy节点的random
        temp = head;
        while (temp != null) {
            Node copyNode = temp.next;
            if (temp.random != null) {
                copyNode.random = temp.random.next;
            }
            temp = copyNode.next;
        }

        // 3.设置copy节点的next(其实也就是把新链表拆出来了)
        temp = head;
        Node copyHead = head.next;
        Node copyNode = copyHead;
        while (temp != null) {
            temp.next = temp.next.next;
            temp = temp.next;
            if (copyNode.next != null) {
                copyNode.next = copyNode.next.next;
                copyNode = copyNode.next;
            }
        }
        return copyHead;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
