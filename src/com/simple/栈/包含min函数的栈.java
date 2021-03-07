package com.simple.栈;

import java.util.LinkedList;

/**
 * @author: hotpot
 * @since: 2021/03/07
 */
public class 包含min函数的栈 {
    private LinkedList<Integer> stack;
    private LinkedList<Integer> helpStack;

    /** initialize your data structure here. */
    public 包含min函数的栈() {
        stack = new LinkedList<>();
        helpStack = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
        if (helpStack.isEmpty()) {
            helpStack.push(x);
            return;
        }

        Integer top = helpStack.peek();
        if (x < top) {
            helpStack.push(x);
        } else {
            helpStack.push(top);
        }
    }

    public void pop() {
        stack.pop();
        helpStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return helpStack.peek();
    }
}
