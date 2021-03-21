package com.medium.递归;

import datastructure.TreeNode;

/**
 * @author: hotpot
 * @since: 2021/03/21
 */
public class 二叉树中的最大路径和 {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return ans;
    }

    public int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftPrice = Math.max(maxGain(root.left), 0);
        int rightPrice = Math.max(maxGain(root.right), 0);
        int price = root.val + leftPrice + rightPrice;
        ans = Math.max(ans, price);
        return root.val + Math.max(leftPrice, rightPrice);
    }
}
