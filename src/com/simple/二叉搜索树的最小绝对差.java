package com.simple;

import datastructure.TreeNode;

/**
 * @author hotpot
 * @since 2020-04-18 21:49:38
 * 思路：
 * 二叉搜索树中序遍历是有序的，
 * 所以最小绝对值差肯定是中序遍历中相邻的节点，
 * 用pre记录前一个节点，只比较相邻节点就行了
 */
public class 二叉搜索树的最小绝对差 {
    private int ans = Integer.MAX_VALUE;
    private Integer preValue;

    public int getMinimumDifference(TreeNode root) {
        dfsMiddle(root);
        return ans;
    }

    private void dfsMiddle(TreeNode root) {
        if (root == null) {
            return;
        }
        dfsMiddle(root.left);
        if (preValue != null) {
            int abs = Math.abs(root.val - preValue);
            if (abs < ans) {
                ans = abs;
            }
        }
        preValue = root.val;
        dfsMiddle(root.right);
    }
}
