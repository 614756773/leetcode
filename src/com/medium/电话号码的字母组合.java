package com.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/8/22
 * @Author: qinzhu
 */
public class 电话号码的字母组合 {
    private List<String> result = new ArrayList<>();
    private char[][] chars = new char[10][];
    private int[] numbers;

    public static void main(String[] args) {
        电话号码的字母组合 o = new 电话号码的字母组合();
        List<String> list = o.letterCombinations("2");
        System.out.println(list);
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return result;
        }

        chars[2] = new char[]{'a', 'b', 'c'};
        chars[3] = new char[]{'d', 'e', 'f'};
        chars[4] = new char[]{'g', 'h', 'i'};
        chars[5] = new char[]{'j', 'k', 'l'};
        chars[6] = new char[]{'m', 'n', 'o'};
        chars[7] = new char[]{'p', 'q', 'r', 's'};
        chars[8] = new char[]{'t', 'u', 'v'};
        chars[9] = new char[]{'w', 'x', 'y', 'z'};

        char[] input = digits.toCharArray();
        numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = input[i] - 48;
        }

        for (int i = 0; i < chars[numbers[0]].length; i++) {
            dfs(chars[numbers[0]][i] + "", 1);
        }
        return result;
    }

    private void dfs(String preStr, int level) {
        if (level == numbers.length) {
            result.add(preStr);
        } else {
            char[] chars = this.chars[numbers[level]];
            for (char ch : chars) {
                dfs(preStr + ch, level + 1);
            }
        }
    }
}
