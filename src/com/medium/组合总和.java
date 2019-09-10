package com.medium;

import java.util.*;

/**
 * @Date: 2019/9/3
 * @Author: qinzhu
 */
public class 组合总和 {

    private static int elements[];
    private static List<List<Integer>> result = new ArrayList<>();
    public static void main(String[] args) {
        组合总和 runner = new 组合总和();
        int[] candidates = {1,2,3};
        List<List<Integer>> lists = runner.combinationSum(candidates, 3);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        elements = candidates;
        findCombinationSum(target, elements.length, new Stack<>());
        return result;
    }

    // 输入: candidates = [2, 3, 6, 7]，target = 7]
    private void findCombinationSum(int target, int end, Stack<Integer> group) {
        for (int i = 0; i < end; i++) {
            int remaining = target - elements[i];
            if (remaining == 0) {
                group.push(elements[i]);
                result.add(new ArrayList<>(group));
                group.pop();
            } else if (remaining > 0) {
                group.push(elements[i]);
                findCombinationSum(remaining, end - 1, group);
                group.pop();
            }
        }
    }


}
