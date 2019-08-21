package com.simple;

import java.util.LinkedList;

/**
 * @Date: 2019/8/21
 * @Author: qinzhu
 */
public class 有效的括号 {
    public static void main(String[] args) {
        System.out.println(isValid("(("));
    }

    public static boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        if (s.length() == 1) {
            return false;
        }

        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            Character peek = stack.peek();
            char c = chars[i];
            if (peek != null && pipei(c, peek)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    private static boolean pipei(char c, Character peek) {
        int abs = Math.abs(c - peek);
        return abs < 3 && abs != 0;
    }
}
