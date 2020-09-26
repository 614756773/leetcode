package com.medium;

import datastructure.TreeNode;

import java.util.ArrayList;
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
        dfs(root, 0, sum, new ArrayList<>());
        return res;
    }

    private void dfs(TreeNode root, int curSum, int target, List<Integer> tmpList) {
        if (root == null) {
            return;
        }

        curSum += root.val;
        tmpList.add(root.val);
        if (curSum == target && root.left == null && root.right == null) {
            res.add(new ArrayList<>(tmpList));
        }

        dfs(root.left, curSum, target, tmpList);
        dfs(root.right, curSum, target, tmpList);

        int rmIndex = -1;
        for (int i = tmpList.size() - 1; i >= 0; i--) {
            if (tmpList.get(i) == root.val) {
                rmIndex = i;
                break;
            }
        }
        tmpList.remove(rmIndex);
    }

}
