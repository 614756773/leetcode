package com;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qinzhu
 * @since: 2020/12/29
 */
public class Main {
    static List<List<String>> res = new ArrayList<>();

    public static void main(String[] args) {
        全排列();
    }

    private static void 全排列() {
        int[] a = new int[]{1, 2,3};
        int[] visited = new int[4];
        dfs(a, 1, visited, new ArrayList<>());
        System.out.println(res);
    }

    private static void dfs(int[] a, int level, int[] visited, List<String> tmp) {
        if (level > a.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            tmp.add(String.valueOf(a[i]));
            dfs(a, level + 1, visited, tmp);
            tmp.remove(String.valueOf(a[i]));
            visited[i] = 0;
        }
    }
}
