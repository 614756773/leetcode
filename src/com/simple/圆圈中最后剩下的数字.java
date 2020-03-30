package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/30
 */
public class 圆圈中最后剩下的数字 {
    public static void main(String[] args) {
        System.out.println(new 圆圈中最后剩下的数字().lastRemaining(5, 3));
    }

    /**
     * 解法二：总结规律使用公式
     */
    public int lastRemaining(int n, int m) {
        if (n == 1) {
            return 0;
        }
        if (m == 1) {
            return n - 1;
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    /**
     * 解法一：循环链表，会超时
     */
    public int lastRemaining_(int n, int m) {
        if (n == 1) {
            return 0;
        }
        if (m == 1) {
            return n - 1;
        }
        Node head = new Node(0);
        Node p = head;
        for (int i = 1; i < n; i++) {
            p.next = new Node(i);
            p = p.next;
        }
        p.next = head;

        p = head;
        while (p.next != p) {
            p = move(p, m);
        }
        return p.val;
    }

    private Node move(Node node, int m) {
        Node pre = node;
        Node cur = node;
        for (int i = 0; i < m - 1; i++) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        cur.next = null;
        return pre.next;
    }

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}
