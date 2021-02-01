package com.medium.链表;

import datastructure.Node;

import java.util.LinkedList;

/**
 * @author qinzhu
 * @since 2021/2/1
 */
public class 扁平化多级双向链表 {
    int level = 0;
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        connection(node1, node2);
        connection(node2, node3);
        connection(node3, node4);
        connection(node4, node5);
        connection(node5, node6);

        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        connection(node7, node8);
        connection(node8, node9);
        connection(node9, node10);
        node8.child = node11;
        node3.child = node7;
        connection(node11, node12);

//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        node1.child = node2;
//        node2.child = node3;

        Node flatten = new 扁平化多级双向链表().flatten_(node1);
        while (flatten != null) {
            System.out.println(flatten.val);
            flatten = flatten.next;
        }
    }

    /**
     * 另一种思路：侧着看，这道题就是先序遍历二叉树
     * 1.先序遍历，存放在队列中
     * 2.将结果重新构造成链表
     */
    private Node flatten_(Node head) {
        if (head == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        inQueue(queue, head);
        head = queue.removeFirst();
        Node cur = head;
        cur.child = null;
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            node.child = null;
            cur.next = node;
            node.prev = cur;
            cur = cur.next;
        }
        return head;
    }

    private void inQueue(LinkedList<Node> queue, Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        while (cur != null) {
            queue.addLast(cur);
            if (cur.child != null) {
                inQueue(queue, cur.child);
            }
            cur = cur.next;
        }

    }

    private static void connection(Node head, Node node8) {
        head.next = node8;
        node8.prev = head;
    }

    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    private Node dfs(Node node) {
        if (node == null) {
            return node;
        }
        int val = node.val;
        helper(val);
        while (node.next != null && node.child == null) {
            node = node.next;
        }

        // 将链表尾节点返回
        if (node.next == null && node.child == null) {
            return node;
        }

        Node oldNext = node.next;
        node.next = node.child;
        node.child.prev = node;
        node.child = null;
        level++;
        Node tail = dfs(node);
        level--;
        helper(val);
        tail.next = oldNext;
        if (oldNext == null) {
            return tail;
        }
        oldNext.prev = tail;

        // 后面可能还需要扁平处理
        level++;
        tail = dfs(oldNext);
        level--;
        helper(val);
        return tail;
    }

    /**
     * 用来辅助查看递归树的
     */
    private void helper(int val) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println("level:" + level + ",val:" + val);
    }
}
