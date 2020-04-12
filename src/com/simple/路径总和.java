package com.simple;

import datastructure.TreeNode;

public class 路径总和 {
    private boolean flag = false;
    private int target = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1);
        TreeNode l = new TreeNode(-2);
        root.left = l;
        l.right = new TreeNode(3);
        TreeNode ll = new TreeNode(1);
        l.left = ll;
        l.left = new TreeNode(-1);
        TreeNode r = new TreeNode(-3);
        root.right = r;
        r.left = new TreeNode(-2);
        System.out.println(new 路径总和().hasPathSum_(root, -1));
        System.out.println(new 路径总和().hasPathSum(root, -1));
    }

    public boolean hasPathSum_(TreeNode root, int sum) {
        if (root == null)
            return false;

        sum -= root.val;
        if ((root.left == null) && (root.right == null))
            return (sum == 0);
        return hasPathSum_(root.left, sum) || hasPathSum_(root.right, sum);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        this.target = sum;
        int val = root.val;
        if (root.left != null) {
            dfsCompute(val, root.left);
        }
        if (root.right != null) {
            dfsCompute(val, root.right);
        }

        return flag;
    }

    private void dfsCompute(int preSum, TreeNode node) {
        int sum = preSum + node.val;
        if (sum > target) {
            return;
        }
        if (sum == target) {
            if (isLeaf(node)) {
                flag = true;
            }
            return;
        }

        if (node.left != null) {
            dfsCompute(sum, node.left);
        }
        if (node.right != null) {
            dfsCompute(sum, node.right);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
