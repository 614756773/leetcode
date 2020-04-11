package com.simple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 二进制手表 {
    private int[] num = new int[]{1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
    private boolean[] visited = new boolean[num.length];
    private int n;

    public static void main(String[] args) {
        System.out.println(new 二进制手表().readBinaryWatch(1));
    }

    public List<String> readBinaryWatch(int n) {
        List<String> ans = new ArrayList<>();
        if (n < 1) {
            return Collections.singletonList("0:00");
        }

        this.n = n;
        backtrace(ans, 0, 0);
        return ans;
    }

    private void backtrace(List<String> ans, int start, int level) {
        if(level == n) {
            append(ans);
            return;
        }
        for (int i = start; i < num.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            backtrace(ans, i, level + 1);
            visited[i] = false;
        }
    }

    private void append(List<String> ans) {
        int h = 0;
        int m = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                continue;
            }
            // 时
            if (i < 4) {
                h += num[i];
                if (h >= 12) {
                    return;
                }
            } else {
                // 分
                m += num[i];
                if (m >= 60) {
                    return;
                }
            }
        }

        StringBuilder sb = new StringBuilder().append(h).append(":");
        if (m < 10) {
            sb.append("0");
        }
        ans.add(sb.append(m).toString());
    }
}
