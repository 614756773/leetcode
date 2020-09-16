package com.simple;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/9/16
 * 思路：递归中序遍历存入list，然后取出来构造结果
 * 思路2：使用哑结点，并且不使用额外的空间
 */
public class 递增顺序查找树_897 {

    private TreeNode dump = new TreeNode(-1);
    private TreeNode pre = dump;

    public TreeNode increasingBST_2(TreeNode root) {
        if (root == null) {
            return null;
        }
        increasingBST_2(root.left);

        pre.right = root;
        root.left = null;
        pre = root;

        increasingBST_2(root.right);
        return dump.right;
    }

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        TreeNode newRoot = list.get(0);
        TreeNode pre = newRoot;
        for (int i = 1; i < list.size(); i++) {
            TreeNode node = list.get(i);
            pre.right = node;
            pre.left = null;
            pre = node;
        }
        return newRoot;
    }

    private void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
}
