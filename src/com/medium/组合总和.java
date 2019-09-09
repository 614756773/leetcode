package com.medium;

import dataStructure.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;

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
        backtrack(result, candidates, target, 0, new LinkedList<>());
        return result;
    }

    /*    输入: candidates = [2,3,5], target = 8,
        所求解集为:
                [
                  [2,2,2,2],
                  [2,3,3],
                  [3,5]
                ]*/
    private void backtrack(List<List<Integer>> result, int[] candidates, int target, int currentIndex, LinkedList<Integer> group) {
        int currentNumber = candidates[currentIndex];
        int tmpResult = reduce(group) + currentNumber;
        if (tmpResult == target) {
            group.add(currentNumber);
            result.add(new ArrayList<>(group));
            // TODO...
            return;
        }

        if (tmpResult < target) {
            group.add(currentNumber);
            if (reduce(group) + candidates[currentIndex] <= target) {
                backtrack(result, candidates, target, currentIndex, group);
            }
            return;
        }

        if (tmpResult > target) {
            group.removeLast();
        }
    }

    private int reduce(List<Integer> group) {
        return group.stream().reduce((a, b) -> a + b).orElse(0);
    }
}
