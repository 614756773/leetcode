package com.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/9/2
 * @Author: qinzhu
 */
public class 括号生成 {

    static int n;

    public static void main(String[] args) {
        List<String> list = generateParenthesis(1);
        System.out.println(list);
    }

    public static List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        括号生成.n = n;
        backtrack(result, "", 0, 0);
        return result;
    }

    private static void backtrack(ArrayList<String> result, String str, int open, int close) {
        if (str.length() == n * 2) {
            result.add(str);
            return;
        }

        if (open < n) {
            backtrack(result, str + "(", open + 1, close);
        }
        if (close < open) {
            backtrack(result, str + ")", open, close + 1);
        }

    }
}
