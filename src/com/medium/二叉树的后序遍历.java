package com.medium;

import datastructure.TreeNode;

import java.util.*;

/**
 * @author qinzhu
 * @since 2020/9/29
 * 迭代思路：
 * 1.使用栈
 * 2.先顺着向下压入所有的左子树节点
 * 3.然后压入右子树节点，并且标记当前节点压入过右子树，把这种当前节点记为RP状态（就是单词right pushed）
 * 4.弹出栈顶元素，若该节点是RP这种类型则输出，否则压入该节点的右子树并且将该节点记录为RP状态
 * ps：还有一个点，需要记录当前节点是否压入过左子树，不然当前节点从栈出弹出时会继续压入左子树。。。无限循环
 */
public class 二叉树的后序遍历 {
    private List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        root.left = l;
        TreeNode r = new TreeNode(3);
        root.right = r;
        TreeNode ll = new TreeNode(4);
        l.left = ll;
        TreeNode rr = new TreeNode(5);
        r.right = rr;
        System.out.println(new 二叉树的后序遍历().postorderTraversal_(root));
    }

    /**
     * 迭代
     */
    public List<Integer> postorderTraversal_(TreeNode root) {
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        // rp即right pushed的缩写，该set存储那些已经压入过右子树的节点，使用该set来确认节点是否应该被输出
        Set<String> rpSet = new HashSet<>();
        // lp即left pushed的缩写，该set存储那些已经压入过左子树的节点，使用该set防止重复操作元素
        Set<String> lpSet = new HashSet<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            // 如果当前节点已经压入过右子树，那么则输出，并且要从rpSet中移除
            if (rpSet.contains(cur.toString())) {
                result.add(stack.pop().val);
                rpSet.remove(cur.toString());
                continue;
            }

            // 左子树不为空，并且没有入过栈
            if (cur.left != null && !lpSet.contains(cur.toString())) {
                stack.push(cur.left);
                lpSet.add(cur.toString());
                continue;
            } else {
                lpSet.add(cur.toString());
            }

            if (cur.right != null) {
                stack.push(cur.right);
            }
            // 走到这一步，说明当前节点压入了右子树，或者右子树为空
            rpSet.add(cur.toString());
        }
        return result;
    }

    /**
     * 递归
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        if (root.left != null) {
            postorderTraversal(root.left);
        }
        if (root.right != null) {
            postorderTraversal(root.right);
        }
        result.add(root.val);
        return result;
    }
}
