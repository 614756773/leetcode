package com.swordoffer;

import java.util.Stack;

/**
 * @author: qinzhu
 * @since: 2020/09/25
 * 思路：存放新元素都到stack1，删除元素都在stack2中操作
 */
public class 用两个栈实现队列_9 {
    class CQueue {
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public CQueue() {
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty() && stack1.isEmpty()) {
                return -1;
            }

            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }

            return stack2.pop();
        }
    }
}
