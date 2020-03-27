package com.simple;

import dataStructure.TreeNode;

/**
 * @author qinzhu
 * @since 2020/3/27
 */
public class 将有序数组转换为二叉搜索树 {
    public static void main(String[] args) {

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
        int mid; // TODO
        return null;
    }
}
