package com.medium;

import datastructure.ListNode;

/**
 * @author qinzhu
 * @since 2020/11/13
 * 思路： 模拟过程
 * 1. 求得链表长度为len
 * 2. 根据k求得子链表的平均长度为ave，ave=len/k。
 *      若len%k不为0，则假设其值为r，如果r值为2则说明前两个子链的长度要额外+1
 *      如果r值为3则说面前三个子链的长度要额外+1....
 * 3. 根据ave和r计算出当前子链的长度为slen，
 *      使用head作为哨兵记录当前子链表的头结点，
 *      向前移动slen-1步得到节点cur，
 *      然后进行断链操作，同时记录结果，更新head，代码如下：
 *          result[i] = head;
 *          head = cur;
 *          cur.next = null;
 *
 */
public class 分隔链表 {
    public static void main(String[] args) {
        ListNode cur = new ListNode(1);
        ListNode root = cur;
        for (int i = 2; i <= 4; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode[] a = new 分隔链表().splitListToParts(root, 5);
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] result = new ListNode[k];
        int len = computeLen(root);
        if (len < 0) {
            return result;
        }
        if (len < k) {
            return mode1(root, k, len);
        }
        return mode2(root, k, len);
    }

    /**
     * 模式一：
     * 假设只有5个节点，却要切分6次
     */
    private ListNode[] mode1(ListNode root, int k, int len) {
        ListNode[] result = new ListNode[k];
        ListNode head = root;
        for (int i = 0; i < len; i++) {
            result[i] = head;
            ListNode cur = head;
            head = head.next;
            cur.next = null;
        }
        return result;
    }

    /**
     * 模式二：
     * 切分的子链数小于等于原链表节点数
     */
    private ListNode[] mode2(ListNode root, int k, int len) {
        ListNode[] result = new ListNode[k];
        int average = len / k;
        int r = len % k;

        ListNode head = root;
        // 将链表分割成k份需要操作k - 1轮
        for (int i = 0; i < k - 1; i++) {
            ListNode cur = head;
            // 开始移动指针
            for (int j = 0; j < average - 1; j++) {
                cur = cur.next;
            }
            // 如果不能完全的平均切割链表，最开始的某些子链表就多1个节点
            if (r > 0) {
                cur = cur.next;
                r--;
            }
            result[i] = head;
            head = cur.next;
            cur.next = null;
        }

        // 最后一个子链表
        if (k <= len) {
            result[k - 1] = head;
        }
        return result;
    }

    private int computeLen(ListNode root) {
        int count = 0;
        while (root != null) {
            count++;
            root = root.next;
        }
        return count;
    }
}
