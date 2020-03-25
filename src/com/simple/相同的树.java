package com.simple;

import dataStructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/3/25
 */
public class 相同的树 {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        a.left = new TreeNode(2);
        a.right = new TreeNode(1);
        TreeNode b = new TreeNode(1);
        b.left = new TreeNode(2);
        b.right = new TreeNode(1);
        System.out.println(new 相同的树().isSameTree(a, b));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null && p.val != q.val) {
            return false;
        }

        if (p != null && q == null || p == null && q != null) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
