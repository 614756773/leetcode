package com.medium;

import java.util.LinkedList;

/**
 * @author: qinzhu
 * @since: 2020/09/28
 * 思路：
 * 方式一，使用队列一层层的遍历构建列表，访问队列前先查询得知有n个元素，于是就知道本次迭代访问n个元素，他们属于同一层
 * 方式二，使用哑结点，访问当前层的时候把下一层串联起来
 * 方式二效率更高
 */
public class 填充每个节点的下一个右侧节点指针II {
    public Node connect_(Node root) { 
        if (root == null) {
            return null;
        }

        // 当前层的首节点，用来访问当前层的所有节点。因为该解法需要在访问当前层的时候把下一层串联起来
        Node curLevelHead = root;

        while (curLevelHead != null) {
            Node cur = curLevelHead;

            // 哑结点，有两个作用
            // 1.用来串联下一层的各个节点作为链表
            // 2.记录下一层节点的首地址
            Node dummy = new Node(-1);
            Node pre = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            // 这儿需要特别注意，dummy的next是执行下一层的首节点的
            // 在访问本层的第一个节点时，pre引用的dummy，然后将其next指向了本层的第一个子节点也就是下一层的首节点
            curLevelHead = dummy.next;
        }
        return root;
    }
    
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            // 访问一层的数据，一共{size}个元素
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                // 弹出当前节点，并且其next指向同层级的下一个节点
                Node node = queue.pop();
                // 本层的最后一个元素的next指向null
                node.next = (i == size - 1) ? null : queue.peek();

                // 把当前节点的子节点入队，访问下一层的时候使用
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
