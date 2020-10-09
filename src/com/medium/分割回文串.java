package com.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/10/9
 * 思路，回溯：
 * 每次都从当前字符串起始位置（其位置记为begin）开始往后找，找到某一个字符（其位置记为end）作为结束位置时为回文串，
 * 于是把这一部分字符串截取掉，递归处理后面的字符。
 * 同时在当前函数中（还没有递归到下一层），仍然以begin开始往后找，找到比end还后面的字符作为结束位置并且为回文串，于是把这一部分字符串截取掉，递归处理后面的字符。
 * .....
 */
public class 分割回文串 {
    private List<List<String>> res = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(new 分割回文串().partition("aabbad"));
    }

    public List<List<String>> partition(String s) {
        backtracking(s, new ArrayList<>());
        return res;
    }

    private void backtracking(String s, List<String> list) {
        if (s == null || s.length() == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(0, i + 1);
            if (isPalindrome(substring)) {
                list.add(substring);
                backtracking(s.substring(i + 1), list);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        int len = str.length();
        for (int left = 0, right = len - 1; left < right; left++, right--) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
        }
        return true;
    }
}
