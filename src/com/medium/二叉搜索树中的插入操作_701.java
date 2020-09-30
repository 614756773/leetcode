package com.medium;

import datastructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/9/30
 * 思路：
 * 根据二叉搜索树的性质，如果val比当前节点小则往左走，如果比当前节点大则往右走
 * 当遇到当前节点的子节点为null时，就把val插入
 */
public class 二叉搜索树中的插入操作_701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        while (true) {
            if (val < cur.val) {
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    return root;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    return root;
                }
                cur = cur.right;
            }
        }
    }
}
