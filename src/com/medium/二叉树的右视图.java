package com.medium;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hotpot
 * @since 2020-04-23 22:51:23
 * 思路：
 * 用dfs层级遍历，
 * 没添加完一个层级就插入一个levelSplit节点，用于标志该层级已结束
 * 在取数据的时候若遇到levelSplit，则说明之前的节点就是一层级最右侧的节点，也就是一个右视图的结果
 */
public class 二叉树的右视图 {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);
        // levelSplit用来分割层级
        TreeNode levelSplit = new TreeNode(0);
        queue.addLast(levelSplit);
        TreeNode preNode = root;

        while (queue.size() != 1) {
            TreeNode node = queue.removeFirst();
            if (node == levelSplit) {
                ans.add(preNode.val);
                queue.addLast(levelSplit);
                continue;
            }
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
            preNode = node;
        }

        ans.add(preNode.val);
        return ans;
    }
}
