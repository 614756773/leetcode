package com.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/9/3
 * @Author: qinzhu
 */
public class 组合总和 {
    private List<List<Integer>> result = new ArrayList<>();
    private int target;
    private int[] candidates;
    private int[] visited;

    public static void main(String[] args) {
        组合总和 runner = new 组合总和();
        int[] candidates = {35, 29, 32, 40, 44, 33, 39, 23, 20, 36, 42, 22, 48, 25, 47, 26, 37, 21, 27, 41, 46, 49, 30, 43, 28, 34, 31, 24, 38};
        List<List<Integer>> lists = runner.combinationSum(candidates, 72);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        this.target = target;
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.visited = new int[candidates.length];
        dfsScan(0, 0);
        return result;
    }

    private void dfsScan(int start, int preSum) {
        if (start > candidates.length) {
            return;
        }

        if (preSum == target) {
            result.add(produceGroup(visited));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int sum = preSum + candidates[i];
            // 剪枝
            if (sum > target) {
                break;
            }
            visited[i]++;
            dfsScan(i, sum);
            visited[i]--;
        }
    }

    private List<Integer> produceGroup(int[] visited) {
        List<Integer> group = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i]; j++) {
                group.add(candidates[i]);
            }
        }
        return group;
    }

}
