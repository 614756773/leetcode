package com.medium.递归;

import datastructure.TreeNode;

/**
 * @author: hotpot
 * @since: 2021/03/21
 */
public class 完全二叉树的节点个数 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLevel = findLevel(root.left);
        int rightLevel = findLevel(root.right);
        // 左右子树层高相等，说明左子树是满二叉树
        // 于是用2^level - 1来求左子树节点数量
        if (leftLevel == rightLevel) {
            return (1 << leftLevel) - 1 + countNodes(root.right) + 1;
        } else {
            // 否则说明右子树是满二叉树
            return (1 << rightLevel) - 1 + countNodes(root.left) + 1;
        }
    }

    private int findLevel(TreeNode node) {
        int level = 0;
        while (node != null) {
            level++;
            node = node.left;
        }
        return level;
    }
}
