package com.medium;

public class 有效括号的嵌套深度 {
    public int[] maxDepthAfterSplit(String seq) {
        char[] chars = seq.toCharArray();
        int[] ans = new int[chars.length];

        char pre = '-';
        int[] value = new int[]{0, 1};
        int cur = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == pre) {
                ans[i] = value[++cur % 2];
            } else {
                ans[i] = value[cur % 2];
            }
            pre = c;
        }
        return ans;
    }
}
