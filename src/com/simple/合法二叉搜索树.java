package com.simple;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/5/13
 * 思路：
 *  中序遍历校验是否为升序
 *  二叉搜索树一定要用中序遍历，递归判断一颗最小树的左右中节点是行不通的
 */
public class 合法二叉搜索树 {
    private List<Integer> list = new ArrayList<>();

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        dfs(root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) >= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            dfs(root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            dfs(root.right);
        }
    }

    public boolean isValidBST_(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null && left.val >= root.val) {
            return false;
        }
        if (right != null && right.val <= root.val) {
            return false;
        }

        boolean lb = true;
        if (left != null) {
            lb = isValidBST_(left);
        }
        boolean rb = true;
        if (right != null) {
            rb = isValidBST_(right);
        }
        return lb && rb;
    }
}
