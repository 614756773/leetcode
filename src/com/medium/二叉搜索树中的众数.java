package com.medium;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/4/16
 * 思路：
 * 首先这是个二叉搜索树，所以用中序遍历就是个有序的序列
 * 用curCount和maxCount两个指针来记录当前数字的次数以及众数的次数
 * 当`当前次数`等于`众数次数`时，则直接添加进结果集
 * 当`当前次数`大于`众数次数`时，则说明出现了新的众数，把之前的结果清空，然后将该数加入到结果集
 */
public class 二叉搜索树中的众数 {
    private final List<Integer> list = new ArrayList<>();
    private int curCount = 0;
    private int maxCount = 0;
    private TreeNode pre;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(4);
        TreeNode r = new TreeNode(10);
        root.right = r;
        r.left = new TreeNode(10);
        int[] ans = new 二叉搜索树中的众数().findMode(root);
        System.out.println(ans);
    }

    public int[] findMode(TreeNode root) {
        dfsScan(root);
        int[] ans = new int[list.size()];
        int index = 0;
        for (Integer v : list) {
            ans[index++] = v;
        }
        return ans;
    }

    private void dfsScan(TreeNode root) {
        if (root == null) {
            return;
        }
        dfsScan(root.left);
        if (pre != null && pre.val == root.val) {
            curCount++;
        } else {
            curCount = 0;
        }
        if (curCount == maxCount) {
            list.add(root.val);
        } else if (curCount > maxCount) {
            maxCount = curCount;
            list.clear();
            list.add(root.val);
        }
        pre = root;
        dfsScan(root.right);
    }
}
