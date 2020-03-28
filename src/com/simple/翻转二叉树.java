package com.simple;

import dataStructure.TreeNode;

/**
 * 思路：将节点的左右孩子交换，然后递归处理左右孩子
 */
public class 翻转二叉树 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
