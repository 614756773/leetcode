package com.simple;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/5/13
 * 思路：
 *  先中序遍历，把结果用list存储起来
 *  然后遍历list去取目标元素的下一个元素
 */
public class 后继者 {
    private List<TreeNode> list = new ArrayList<>();

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        dfs(root);
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            if (node == p) {
                int nextIndex = i + 1;
                return nextIndex < list.size() ? list.get(nextIndex) : null;
            }
        }
        return null;
    }

    private void dfs(TreeNode node) {
        TreeNode left = node.left;
        if (left != null) {
            dfs(left);
        }
        list.add(node);
        TreeNode right = node.right;
        if (right != null) {
            dfs(right);
        }
    }
}
