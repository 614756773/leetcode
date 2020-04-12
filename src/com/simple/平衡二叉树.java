package com.simple;

import datastructure.TreeNode;

/**
 * 思路：递归获取左右子树的高度进行比较，如果差值大于1则不平衡，返回-1
 * 当获取的左右子树高度为-1时，说明子树都已经不平衡了，直接返回-1
 */
public class 平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        int LH = dfsLevel(root.left);
        int RH = dfsLevel(root.right);
        return LH != -1 && RH != -1 && Math.abs(LH - RH) <= 1;
    }

    /**
     * @return 获取当前节点的高度
     */
    private int dfsLevel(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int LH = dfsLevel(node.left);
        int RH = dfsLevel(node.right);
        if (LH == -1 || RH == -1) {
            return -1;
        }
        if (Math.abs(LH - RH) > 1) {
            return -1;
        }
        return Math.max(LH, RH) + 1;
    }

}
