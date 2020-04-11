package com.medium;

import java.util.*;

public class 组合总和II {
    private boolean[] visited;
    private List<List<Integer>> result = new ArrayList<>();
    private int[] candidates;
    private int target;

    public static void main(String[] args) {
        System.out.println(new 组合总和II().combinationSum2(new int[]{1, 1, 2, 5, 6, 7}, 8));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return result;
        }
        visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.target = target;

        dfsScan(0, 0);
        deduplication();
        return result;
    }

    private void deduplication() {
        Map<Integer, List<Integer>> map = new HashMap<>((int)(result.size() / 0.75) + 1);
        result.forEach(e -> {
            map.put(e.hashCode(), e);
        });
        result = new ArrayList<>(map.size());
        map.forEach((k,v) -> {
            result.add(v);
        });
    }

    private void dfsScan(int start, int preSum) {
        if (start > candidates.length) {
            return;
        }

        if (preSum == target) {
            result.add(produceGroup(visited, start));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (visited[i]) {
                continue;
            }

            int sum = preSum + candidates[i];
            if (sum > target) {
                break;
            }

            visited[i] = true;
            dfsScan(i, sum);
            visited[i] = false;
        }
    }

    private List<Integer> produceGroup(boolean[] visited, int end) {
        List<Integer> group = new ArrayList<>();
        for (int i = 0; i <= end; i++) {
            if (visited[i]) {
                group.add(candidates[i]);
            }
        }
        return group;
    }
}
