package com.simple;

import dataStructure.TreeNode;

import java.util.Stack;

/**
 * @author qinzhu
 * @since 2020/3/25
 */
public class 二叉树的最大深度 {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        a.left = new TreeNode(9);
        TreeNode b = new TreeNode(20);
        a.right = b;
        b.left = new TreeNode(15);
        b.right = new TreeNode(7);
        new 二叉树的最大深度().maxDepth_(a);
    }

    /**
     * 递归实现
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 借助栈实现
     */
    public int maxDepth_(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int result = 0;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));
        while (true) {
            if (stack.size() == 0) {
                break;
            }
            Pair pair = stack.pop();
            int parentLevel = pair.level;
            result = Math.max(result, parentLevel);

            TreeNode left = pair.node.left;
            TreeNode right = pair.node.right;
            if (left != null) {
                stack.push(new Pair(left, 1 + parentLevel));
            }
            if (right != null) {
                stack.push(new Pair(right, 1 + parentLevel));
            }
        }
        return result;
    }
}
class Pair {
    TreeNode node;
    int level;

    public Pair(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }
}
