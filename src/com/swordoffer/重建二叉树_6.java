package com.swordoffer;

import datastructure.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2020/9/25
 * 思路：
 * 1.递归
 * 2.根据前序数组的第一个节点值，找到在中序数组中的位置index
 * 3.index左边的为左子树，右边的为右子树
 * 4.切分成两个子数组，递归下去
 */
public class 重建二叉树_6 {
    Map<Integer, Integer> map;
    int[] preorder;
    int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = inorder.length;
        if (len == 0) {
            return null;
        }
        this.preorder = preorder;
        this.inorder = inorder;
        map = new HashMap<>((int)(len / 0.75) + 1);
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, len - 1, 0, len - 1);
    }

    private TreeNode dfs(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preLeft]);
        Integer index = map.get(preorder[preLeft]);
        int leftNum = index - inLeft;
        int rightNum = inRight - index;
        root.left = dfs(preLeft + 1, preLeft + leftNum, inLeft, index - 1);
        root.right = dfs(preRight - rightNum + 1, preRight, index + 1, inRight);
        return root;
    }

    public static void main(String[] args) {
        int[] per = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
//        int[] per = {3, 9, 20};
//        int[] in = {9, 3, 20};
        TreeNode treeNode = new 重建二叉树_6().buildTree(per, in);
        System.out.println(treeNode);
    }
}
