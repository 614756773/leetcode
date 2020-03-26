package com.simple;

import dataStructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/3/26
 */
public class 二叉树的最小深度 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode b = new TreeNode(20);
        root.right = b;
        b.left = new TreeNode(15);
        b.right = new TreeNode(7);
        System.out.println(new 二叉树的最小深度().minDepth(root));
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfsScanMinDepth(root, 1);
    }

    private int dfsScanMinDepth(TreeNode node, int depth) {
        if (node.left == null && node.right == null) {
            return depth;
        }
        if (node.left == null) {
            return dfsScanMinDepth(node.right, depth + 1);
        }
        if (node.right == null) {
            return dfsScanMinDepth(node.left, depth + 1);
        }
        return Math.min(dfsScanMinDepth(node.left, depth + 1), dfsScanMinDepth(node.right, depth + 1));
    }
}
