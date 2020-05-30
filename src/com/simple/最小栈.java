package com.simple;

import datastructure.CustomStack;

/**
 * @author qinzhu
 * @since 2020/5/12
 * 思路：
 *  使用辅助栈，
 *      当主栈push时，如果push的元素比之前的所有元素都小，则同时放入辅助栈中
 *      当主栈pop时，如果主栈的顶元素和辅助栈元素相同，则把辅助栈的元素也移除掉
 *      这样getMin直接去拿辅助栈栈顶元素值，就是主栈里最小的元素值
 */
public class 最小栈 {
    public static void main(String[] args) {
        最小栈 stack = new 最小栈();
        stack.push(1);
        stack.push(19);
        stack.push(119);
        stack.push(0);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
    }
    private CustomStack<Integer> stack;
    private CustomStack<Integer> secondaryStack;
    /** initialize your data structure here. */
    public 最小栈() {
        stack = new CustomStack<>();
        secondaryStack = new CustomStack<>();
    }

    public void push(int x) {
        stack.push(x);
        Integer min = secondaryStack.top();
        if (min == null || x <= min) {
            secondaryStack.push(x);
        }
    }

    public void pop() {
        Integer top = stack.pop();
        Integer min = secondaryStack.top();
        if (top.equals(min)) {
            secondaryStack.pop();
        }
    }

    public int top() {
        return stack.top();
    }

    public int getMin() {
        return secondaryStack.top();
    }
}
