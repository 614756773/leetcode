package com.simple;

import datastructure.TreeNode;

import java.util.LinkedList;

/**
 * @author qinzhu
 * @since 2020/4/16
 */
public class 二叉搜索树中的众数 {
    private LinkedList<Integer> list = new LinkedList<>();
    private int curCount = 0;
    private int maxCount = 0;
    private TreeNode pre;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode l = new TreeNode(4);
        root.left = l;
        root.right = new TreeNode(10);
        l.left = new TreeNode(4);
        l.right = new TreeNode(6);
        int[] ans = new 二叉搜索树中的众数().findMode(root);
        System.out.println(ans);
    }

    public int[] findMode(TreeNode root) {
        dfsScan(root);
        int[] ans = new int[list.size()];
        return ans;
    }

    private void dfsScan(TreeNode root) {
        if (root == null) {
            return;
        }
        dfsScan(root.left);
        if (pre != null && pre.val == root.val) {
            curCount++;
            if (curCount > maxCount) {
                if (!list.isEmpty()){
                    list.pop();
                }
                list.push(root.val);
                maxCount = curCount;
            }
        }
        pre = root;
        dfsScan(root.right);
    }
}
