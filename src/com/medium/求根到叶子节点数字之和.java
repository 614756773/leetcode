package com.medium;

import datastructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/10/29
 * 思路：dfs
 */
public class 求根到叶子节点数字之和 {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        a.left = new TreeNode(2);
        a.right = new TreeNode(3);
        System.out.println(new 求根到叶子节点数字之和().sumNumbers(a));
    }
    private int result = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return result;
        }
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode node, int num) {
        int nextNum = num * 10 + node.val;
        if (node.left == null && node.right == null) {
            result += nextNum;
            return;
        }

        if (node.left != null) {
            dfs(node.left, nextNum);
        }
        if (node.right != null) {
            dfs(node.right, nextNum);
        }
    }
}
