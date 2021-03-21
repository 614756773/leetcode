package com.medium;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: qinzhu
 * @since: 2020/09/26
 * 思路：深度遍历 + 回溯
 */
public class 路径总和II_113 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        dfs(root, 0, sum, new LinkedList<>());
        return res;
    }

    private void dfs(TreeNode root, int curSum, int target, LinkedList<Integer> tmpList) {
        if (root == null) {
            return;
        }
        if (curSum == target && root.left == null && root.right == null) {
            res.add(new LinkedList<>(tmpList));
            return;
        }

        curSum += root.val;
        tmpList.addLast(root.val);
        dfs(root.left, curSum, target, tmpList);
        dfs(root.right, curSum, target, tmpList);
        // 将35行插入的当前节点给移除掉，这儿就是在回溯
        tmpList.removeLast();
    }

}
