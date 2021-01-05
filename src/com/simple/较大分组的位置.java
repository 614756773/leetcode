package com.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2021/1/5
 */
public class 较大分组的位置 {
    public static void main(String[] args) {
        System.out.println(new 较大分组的位置().largeGroupPositions("abbbcccc"));
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        s = s + "A";

        char pre = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur != pre) {
                if (count >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i - count);
                    list.add(i - 1);
                    result.add(list);
                }
                pre = cur;
                count = 1;
                continue;
            }

            count++;
        }
        return result;
    }
}
