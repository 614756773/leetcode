package com.simple;

import datastructure.TreeNode;

/**
 * @author hotpot
 * @since 2020-05-06 22:58:39
 */
public class 检查平衡性 {
    private boolean blance = true;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n2l = new TreeNode(3);
        TreeNode n2ll = new TreeNode(4);
        root.left = n2;
        root.right = new TreeNode(2);
        n2.left = n2l;
        n2.right = new TreeNode(3);
        n2l.left = n2ll;
        n2l.right = new TreeNode(4);
        System.out.println(new 检查平衡性().isBalanced(root));
    }

    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return blance;
    }

    private int dfs(TreeNode node) {
        // 如果已经有分支搜索到不平衡了，则快速中断dfs，减少不必要的搜索开销
        if (!blance) {
            return -1;
        }

        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        int leftHigh = dfs(node.left);
        int rightHigh = dfs(node.right);
        if (Math.abs(leftHigh - rightHigh) > 1) {
            blance = false;
        }
        return Math.max(leftHigh, rightHigh) + 1;
    }
}
