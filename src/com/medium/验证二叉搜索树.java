package com.medium;

import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/3/26
 * 思路：中序遍历树，如果结果是升序那么就是合法的二叉搜索树
 */
public class 验证二叉搜索树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        System.out.println(new 验证二叉搜索树().isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        dfsScan(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void dfsScan(TreeNode node, List<Integer> list) {
        if (node.left != null) {
            dfsScan(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            dfsScan(node.right, list);
        }
    }
}
