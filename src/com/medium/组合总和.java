package com.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/9/3
 * @Author: qinzhu
 */
public class 组合总和 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, candidates, target, 0, new ArrayList<Integer>());
        return result;
    }

    /*    输入: candidates = [2,3,5], target = 8,
        所求解集为:
                [
                  [2,2,2,2],
                  [2,3,3],
                  [3,5]
                ]*/
    private void backtrack(List<List<Integer>> result, int[] candidates, int target, int current, ArrayList<Integer> group) {

    }
}
