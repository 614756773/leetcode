package com.medium.递归;

import datastructure.TreeNode;

/**
 * @author: hotpot
 * @since: 2021/03/21
 */
public class 二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        // left和right都不为null，说明left和right在root的两侧
        return root;
    }
}
