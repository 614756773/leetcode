package com.medium;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/10/27
 */
public class 二叉树的前序遍历 {
    private List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        a.left = null;
        a.right = new TreeNode(2);
        a.right.left = new TreeNode(3);
        System.out.println(new 二叉树的前序遍历().preorderTraversal_(a));
    }

    /**
     * 非递归方式一：
     * 弹出栈的时候处理元素，并且按顺序将该元素的右孩子和左孩子入栈
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            result.add(top.val);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
        return result;
    }

    /**
     * 非递归方式二：（没有第一种简洁明了，但是这是`中左右`的模拟递归）
     * 图片展示过程：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode-solution/
     * 入栈的时候处理元素，
     * 循环将左孩子入栈（并且同时处理入栈的元素），直到元素没有左孩子。
     * 然后弹出栈顶元素并且将右孩子入栈（并且同时处理入栈的元素）
     */
    public List<Integer> preorderTraversal_(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 非递归方式三：（morris遍历/动态线索二叉树）
     * 时间复杂度O(n)，空间复杂度O(1)
     * 参考：https://zhuanlan.zhihu.com/p/101321696
     *
     * 记作当前节点为cur。
     * 1.如果cur无左孩子，cur向右移动（cur=cur.right）
     * 2.如果cur有左孩子，找到cur左子树上最右的节点，记为mostright
     *      2.a 如果mostright的right指针指向空，让其指向cur，cur向左移动（cur=cur.left）
     *      2.b 如果mostright的right指针指向cur，让其指向空，cur向右移动（cur=cur.right）
     */
/*    public List<Integer> morris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root;
        while (cur != null) {
            // 1.
            if (cur.left == null) {
                cur = cur.right;
            } else { // 2.
                TreeNode mostRight = cur.left;
                while (true) {
                    if (mostRight.right != null && mostRight != cur) {
                        mostRight = mostRight.right;
                    } else {
                        break;
                    }
                }

                // 2.a
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) { // 2.b
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }

        return res;
    }*/
}
