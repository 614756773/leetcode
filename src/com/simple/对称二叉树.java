package com.simple;

import datastructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/3/26
 */
public class 对称二叉树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        root.left = l;
        TreeNode r = new TreeNode(2);
        root.right = r;
        l.left = new TreeNode(3);
        l.right = new TreeNode(4);
        r.left = new TreeNode(4);
        r.right = new TreeNode(3);
        System.out.println(new 对称二叉树().isSymmetric(root));

    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
