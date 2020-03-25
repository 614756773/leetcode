package com.simple;

import dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/3/25
 */
public class 二叉树的所有路径 {
    private List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        a.left = l;
        a.right = new TreeNode(3);
        l.right = new TreeNode(5);
        System.out.println(new 二叉树的所有路径().binaryTreePaths(a));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return result;
        }
        scanPaths(root, new StringBuilder(Integer.toString(root.val)));
        return result;
    }

    private void scanPaths(TreeNode node, StringBuilder s) {
        if (node.left != null) {
            StringBuilder tmp = new StringBuilder(s);
            tmp.append("->").append(Integer.toString(node.left.val));
            scanPaths(node.left, tmp);
        }
        if (node.right != null) {
            StringBuilder tmp = new StringBuilder(s);
            tmp.append("->").append(Integer.toString(node.right.val));
            scanPaths(node.right, tmp);
        }

        if (node.left == null && node.right == null) {
            result.add(s.toString());
        }
    }
}
