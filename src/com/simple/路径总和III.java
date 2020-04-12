package com.simple;

import datastructure.TreeNode;

/**
 * @author hotpot
 * @since 2020-04-12 19:48:25
 * 思路：双重递归
 */
public class 路径总和III {
    private int target;
    private int ans = 0;

    public int pathSum(TreeNode root, int sum) {
        target = sum;
        execute(root);
        return ans;
    }

    private void execute(TreeNode root) {
        if (root == null) {
            return;
        }

        dfsCompute(root, 0);

        execute(root.left);
        execute(root.right);
    }

    private void dfsCompute(TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        if (root.val + sum == target) {
            this.ans++;
        }

        int next = sum + root.val;
        dfsCompute(root.left, next);
        dfsCompute(root.right, next);
    }
}
