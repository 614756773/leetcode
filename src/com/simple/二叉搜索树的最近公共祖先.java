package com.simple;

import datastructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/3/30
 * 思路：首先想到二叉搜索树的特征，即父节点永远是中间值
 * 根据这个特点可以判断p，q节点位于根节点的左侧还是右侧
 */
public class 二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 情形1：p和q在根节点的两侧
        if ((p.val - root.val) * (q.val - root.val) <= 0) {
            return root;
        }
        // 情形2：p在根节点的左侧（因为情形1执行过,所以p,q肯定是同侧,即q也在左侧）
        if (p.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // 情形3：p,q在根节点右侧
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    /**
     * 写法2
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
