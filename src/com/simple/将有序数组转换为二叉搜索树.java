package com.simple;

import datastructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/3/27
 * 特点：二叉搜索树的中序遍历是有序的
 * 思路：根据二叉搜索树的特点优先构造中间节点，然后小的为左子树（递归构造下去），大的为右子树（递归构造下去）
 */
public class 将有序数组转换为二叉搜索树 {
    public static void main(String[] args) {
        TreeNode root = new 将有序数组转换为二叉搜索树().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(root);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        return dfsProduce(nums, 0, nums.length - 1);
    }

    private TreeNode dfsProduce(int[] nums, int left, int right) {
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        if (left > right) {
            return null;
        }
        // mid相对于left的偏移量
        int move = (right - left) % 2 == 0 ? (right - left) / 2 : (right - left + 1) / 2;
        int mid = move + left;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfsProduce(nums, left, mid - 1);
        root.right = dfsProduce(nums, mid + 1, right);
        return root;
    }
}
