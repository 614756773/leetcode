package com.simple;

import dataStructure.TreeNode;

public class 左叶子之和 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfsSum(root.left, true) + dfsSum(root.right, false);
    }

    private int dfsSum(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null && isLeft) {
            return node.val;
        }

        int l = 0,r = 0;
        if (node.left != null) {
            l = dfsSum(node.left,true);
        }
        if (node.right != null) {
            r = dfsSum(node.right, false);
        }
        return l + r;
    }
}
