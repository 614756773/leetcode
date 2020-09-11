package com.medium;

import datastructure.ListNode;
import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/9/10
 * 思路1：队列 + bfs，并且使用一个split节点来标志某一层遍历完毕
 * 思路2：递归 + dfs + 层级构造链表（如果先遍历左子树再遍历右子树，那么需要使用尾插法生成链表；如果先右子树再左子树，则使用头插法即可，此处头插法更方便）
 */
public class 特定深度节点链表 {

    public ListNode[] listOfDepth(TreeNode tree) {
        ListNode[] result = new ListNode[getDepth(tree)];
        dfs(result, tree, 0);
        return result;
    }

    private void dfs(ListNode[] result, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (result[depth] == null) {
            result[depth] = new ListNode(node.val);
        } else {
            ListNode newNode = new ListNode(node.val);
            newNode.next = result[depth];
            result[depth] = newNode;
        }

        dfs(result, node.right, depth + 1);
        dfs(result, node.left, depth + 1);
    }

    private int getDepth(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return Math.max(getDepth(tree.left), getDepth(tree.right)) + 1;
    }








    public ListNode[] listOfDepth_(TreeNode tree) {
        List<ListNode> result = new ArrayList<>();
        if (tree == null) {
            return new ListNode[0];
        }

        TreeNode split = new TreeNode(0);
        List<Integer> tmpContainer = new ArrayList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(tree);
        queue.addLast(split);
        while (!queue.isEmpty() && queue.peek() != split) {
            TreeNode node = queue.removeFirst();
            tmpContainer.add(node.val);
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }

            // queue.size() != 1判断很重要，防止死循环插入split
            TreeNode next = queue.peek();
            if (next == split) {
                queue.addLast(queue.removeFirst());
                result.add(produceListNode(tmpContainer));
            }
        }

        ListNode[] rs = new ListNode[result.size()];
        return result.toArray(rs);
    }

    private ListNode produceListNode(List<Integer> tmpContainer) {
        ListNode head = new ListNode(tmpContainer.remove(0));
        ListNode p = head;
        for (Integer e : tmpContainer) {
            p.next = new ListNode(e);
            p = p.next;
        }
        // 清空，重复利用
        tmpContainer.clear();
        return head;
    }
}
