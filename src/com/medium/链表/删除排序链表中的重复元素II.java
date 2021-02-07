package com.medium.链表;

import datastructure.ListNode;

import static com.util.ListNodeUtil.produceListNodes;

/**
 * @author qinzhu
 * @since 2021/2/5
 * 思路：递归
 *
 * 先考虑最简单的情况，也就是链表为：1->2
 * 那么直接返回head就行，但是可能后面的节点有重复的，所以需要递归处理后续节点，代码变成：
 *      if(head.next.val != head.val) {
 *          head.next = deleteDuplicates(head.next);
 *          return head;
 *      }
 *
 * 接着考虑有重复节点的情况，那么一直向后移动就行：
 *      int val = head.val;
 *      while(head.next.val == val) {
 *          head = head.next;
 *      }
 *      // return head.next;  不能直接return head.next，因为后续节点可能还需要处理，所以得递归
 *      return deleteDuplicates(head.next);
 *
 * 之后把结束条件补上即可：
 *      if(head == null || head.next == null) {
 *         return head;
 *      }
 */
public class 删除排序链表中的重复元素II {
    public static void main(String[] args) {
        ListNode head = produceListNodes(new Integer[]{1,1,1,2,2,3,4,5,5,6});
        ListNode result = new 删除排序链表中的重复元素II().deleteDuplicates(head);
        System.out.println(result);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int val = head.val;
        if (val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }

        while (head.next != null && head.next.val == val) {
            head = head.next;
        }
        return deleteDuplicates(head.next);
    }
}
