package com.medium;

import datastructure.TreeNode;

/**
 * @author: qinzhu
 * @since: 2020/11/24
 * 思路：
 *      方式一：递归dfs
 *
 *      方式二：使用完全二叉树的两个性质
 *          1.若根节点处于第1层，则n层的完全二叉树节点数在[2^(n-1), 2^n-1]范围内
 *          2.节点的索引值的二进制可以表示从根节点到其位置的路径走法
 */
public class 完全二叉树的节点个数 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a_2 = new TreeNode(2);
        root.left = a_2;
        TreeNode a_3 = new TreeNode(3);
        root.right = a_3;
        TreeNode a_4 = new TreeNode(4);
        a_2.left = a_4;
        TreeNode a_5 = new TreeNode(5);
        a_2.right = a_5;
        a_3.left = new TreeNode(6);
        System.out.println(new 完全二叉树的节点个数().countNodes_(root));
    }
    int count = 0;
    /**
     * dfs
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return count;
        }

        count++;
        countNodes(root.left);
        countNodes(root.right);
        return count;
    }

    /**
     * 使用完全二叉树的性质，并且加上二分查找
     */
    public int countNodes_(TreeNode root) {
        int count = 0;
        if (root == null) {
            return count;
        }

        // 首先确定层级
        int level = findLevel(root);
        if (level == 1) {
            return 1;
        }

        // 使用二分法确定最底层的最靠右的节点，令根节点的索引值为1
        int first = 1 << (level - 1);
        int last = (1 << level) - 1;
        int mid;
        while (first < last) {
            mid = (last - first + 1) / 2 + first;
            if (inTree(root, mid, level)) {
                first = mid;
            } else {
                last = mid - 1;
            }
        }
        return first;
    }

    private boolean inTree(TreeNode root, int index, int level) {
        for (int i = level - 2; i >= 0; i--) {
            if (((index >> i) & 1) == 1) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
//        for (int i = 1; i < level; i++) {
//            if ((index >> (level - i - 1) & 1) == 1) {
//                root = root.right;
//            } else {
//                root = root.left;
//            }
//        }
        return root != null;
    }

    private int findLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}
