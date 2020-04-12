package com.medium;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/3/26
 */
public class 二叉树的层次遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> queue = new ArrayList<>(2);
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }
        result.add(Arrays.asList(root.val));
        bfsScan(queue, result);

        return result;
    }

    private void bfsScan(List<TreeNode> queue, List<List<Integer>> result) {
        while (queue.size() != 0) {
            List<Integer> list = new ArrayList<>(queue.size());
            List<TreeNode> newQueue = new ArrayList<>(queue.size() * 2);
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
            result.add(list);
        }
    }
}
