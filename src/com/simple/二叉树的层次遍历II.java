package com.simple;

import dataStructure.TreeNode;

import java.util.*;

/**
 * @author qinzhu
 * @since 2020/3/26
 * 使用队列加递归
 */
public class 二叉树的层次遍历II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<TreeNode> queue = new ArrayList<>();
        result.add(Arrays.asList(root.val));
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }

        bfsScan(result, queue);
        Collections.reverse(result);
        return result;
    }

    private void bfsScan(List<List<Integer>> result, List<TreeNode> queue) {
        while (queue.size() != 0) {
            List<TreeNode> newQueue = new ArrayList<>();
            List<Integer> list = new ArrayList<>(queue.size());
            for (TreeNode node : queue) {
                list.add(node.val);
                if (node.left != null) {
                    newQueue.add(node.left);
                }
                if (node.right != null) {
                    newQueue.add(node.right);
                }
            }
            queue = newQueue;
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
    }
}
