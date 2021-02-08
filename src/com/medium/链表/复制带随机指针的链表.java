package com.medium.链表;


import java.util.HashMap;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2021/2/8
 */
public class 复制带随机指针的链表 {

    public static void main(String[] args) {
        Map<Node, Node> map = new HashMap<>();
        map.forEach((oldNode, newNode) -> {
            newNode.next = map.get(oldNode.next);
            newNode.random = map.get(oldNode.random);
        });
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 1.copy
        Node temp = head;
        while (temp != null) {
            Node next = temp.next;
            Node copyNode = new Node(temp.val);
            temp.next = copyNode;
            copyNode.next = next;
            temp = temp.next.next;
        }

        // 2.复制random节点
        temp = head;
        while (temp != null) {
            Node copyNode = temp.next;
            if (temp.random != null) {
                // 题目中有这种情况，即某些节点的random指针为空
                copyNode.random = temp.random.next;
            }
            temp = temp.next.next;
        }

        // 3.把复制链表拆出来，同时还原旧链表
        Node newHead = head.next;
        temp = newHead;
        while (temp.next != null) {
            temp.next = temp.next.next;
            temp = temp.next;
        }
        return newHead;
    }
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
