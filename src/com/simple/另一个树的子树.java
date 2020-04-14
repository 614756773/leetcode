package com.simple;

import datastructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/4/14
 * 思路：
 * 分2步操作
 * 1.找匹配的节点作为子树的根节点
 * 2.递归判断子树所有节点是否匹配
 */
public class 另一个树的子树 {
    private boolean ans = false;

    public static void main(String[] args) {
        TreeNode s = new TreeNode(6);
        s.left = new TreeNode(1);
        s.right = new TreeNode(4);

        TreeNode t = new TreeNode(6);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);

        System.out.println(new 另一个树的子树().isSubtree(s, t));
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return ans;
        }
        dfsFindRoot(s, t);
        return ans;
    }

    private void dfsFindRoot(TreeNode s, TreeNode t) {
        // 如果有其他地方已经发现匹配了
        // 那么就别再继续找了
        if (ans) {
            return;
        }

        if (s.val == t.val) {
            // 检查是否匹配
            ans = dfsMatch(s, t);
        }

        if (s.left != null) {
            dfsFindRoot(s.left, t);
        }
        if (s.right != null) {
            dfsFindRoot(s.right, t);
        }
    }

    private boolean dfsMatch(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return dfsMatch(s.left, t.left) && dfsMatch(s.right, t.right);
    }
}
