package com.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/9/3
 * @Author: qinzhu
 */
public class 字母大小写全排列 {
    public static void main(String[] args) {
        字母大小写全排列 tmp = new 字母大小写全排列();
        List<String> list = tmp.letterCasePermutation("ab1");
        System.out.println(list);
    }

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return result;
        }

        char[] chars = s.toCharArray();
        backtrack(result, chars, 0);
        return result;
    }

    private void backtrack(List<String> result, char[] chars, int current) {
        if (current == chars.length) {
            result.add(String.valueOf(chars));
            return;
        }

        if (chars[current] >= '0' && chars[current] <= '9') {
            backtrack(result, chars, current + 1);
            return;
        }

        backtrack(result, chars, current + 1);
        chars[current] = switchCase(chars[current]);
        backtrack(result, chars, current + 1);
    }

    private char switchCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);
        } else {
            return (char) (ch - 32);
        }
    }
}
