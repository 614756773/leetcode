package com.medium;

import datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2020/9/25
 * 思路：
 * 后续数组的最后一个节点是根节点，通过这个节点去中序数组中找到对应位置inIndex，
 * 然后中序数组的inIndex左侧就是左子树部分，右侧就是右子树
 * 在递归构造的时候先构造右子树再构造左子树，这样后续数组依次取倒数第1个，倒数第2个...就是对应子树的根节点 方便
 */
public class 从中序与后序遍历序列构造二叉树_106 {
    int[] postorder;
    Map<Integer, Integer> map = new HashMap<>();
    int postTail;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        if (len == 0) {
            return null;
        }

        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        this.postorder = postorder;
        postTail = postorder.length - 1;

        return dfs(0, inorder.length - 1);
    }

    private TreeNode dfs(int inLow, int inHigh) {
        if (inLow > inHigh) {
            return null;
        }
        int value = postorder[postTail--];
        Integer inIndex = map.get(value);
        TreeNode root = new TreeNode(value);
        root.right = dfs(inIndex + 1, inHigh);
        root.left = dfs(inLow, inIndex - 1);
        return root;
    }

}
