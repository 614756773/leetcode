package com.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qinzhu
 * @since 2019/9/11
 */
public class 全排列 {
    public static void main(String[] args) {
        全排列 runner = new 全排列();
        List<List<Integer>> permute = runner.permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        ArrayList<Integer> numList = copy(nums);
        backtrack(result, numList, 0);

        return result;
    }

    private void backtrack(List<List<Integer>> result, ArrayList<Integer> nums, int start) {
        if (start == nums.size()) {
            result.add(new ArrayList<>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            backtrack(result, nums, start + 1);
            Collections.swap(nums, start, i);
        }
    }

    private ArrayList<Integer> copy(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(nums).forEach(list::add);
        return list;
    }
}
