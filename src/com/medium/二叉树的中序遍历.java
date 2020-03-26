package com.medium;

import dataStructure.TreeNode;

import java.util.*;

/**
 * @author qinzhu
 * @since 2020/3/26
 * 递归太简单了，使用栈的方式
 * 思路：1.先把左子树全部入栈（要记录当前节点已被入过栈），2.出栈处理，3.如果当前节点有右子节点则将右节点入栈 4.循环
 */
public class 二叉树的中序遍历 {

    private Set<TreeNode> set = new HashSet<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;
        right.left = new TreeNode(3);
        System.out.println(new 二叉树的中序遍历().inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && !set.contains(node)) {
                stack.push(node.left);
                set.add(node);
                continue;
            }
            stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }
}
