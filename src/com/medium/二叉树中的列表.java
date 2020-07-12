package com.medium;

import datastructure.ListNode;
import datastructure.TreeNode;

/**
 * @author: qinzhu
 * @since: 2020/07/12
 * 思路：暴力法
 * 遍历整棵树，用数的每个节点作为链表的头节点去匹配
 * 匹配时在向下遍历，看当前树的节点作为链表头结点是否满足条件
 */
public class 二叉树中的列表 {

    // 这个方法也能使用，原理和下面的是一样的，只是写起来有点啰嗦
//    public boolean isSubPath(ListNode head, TreeNode root) {
//        LinkedList<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            if (dfs(node, head)) {
//                return true;
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//        }
//        return false;
//    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        return dfs(root, head) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(TreeNode tNode, ListNode lNode) {
        if (lNode == null) {
            return true;
        }
        if (tNode == null) {
            return false;
        }
        if (tNode.val != lNode.val) {
            return false;
        }
        return dfs(tNode.left, lNode.next) || dfs(tNode.right, lNode.next);
    }
}
