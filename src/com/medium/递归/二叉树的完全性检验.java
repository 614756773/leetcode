package com.medium.递归;

import datastructure.TreeNode;

import java.util.LinkedList;

/**
 * @author: hotpot
 * @since: 2021/03/21
 */
public class 二叉树的完全性检验 {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node != null) {
                queue.addLast(node.left);
                queue.addLast(node.right);
            }
            if (node == null && haveNoNullNode(queue)) {
                return false;
            }
        }
        return true;
    }

    private boolean haveNoNullNode(LinkedList<TreeNode> queue) {
        for (TreeNode node : queue) {
            if (node != null) {
                return true;
            }
        }
        return false;
    }
}
