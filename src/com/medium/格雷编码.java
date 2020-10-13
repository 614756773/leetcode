package com.medium;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/10/9
 * 思路：
 *      方法一，使用递归
 *          可以预先得知格雷码的数量是2的n次方，比如n为3，则一共有8个格雷码。在递归时使用此数量来结束递归
 *          使用masks数组做掩码，对当前的格雷码挨个进行异或来求得下一个格雷码（如果在结果集中已经包含了则跳过）
 *      方法二，规律/镜像反射法
 *          看图https://leetcode-cn.com/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd
 */
public class 格雷编码 {
    private int[] masks;
    private int grayCodeNum;
    private LinkedHashSet<Integer> res = new LinkedHashSet<>();
    public static void main(String[] args) {
        List<Integer> x = new 格雷编码().grayCode(0);
        for (Integer integer : x) {
            System.out.println(Integer.toString(integer, 2));
        }
        System.out.println(x);
    }

    public List<Integer> grayCode2(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }

        // gn为正向的数据
        List<Integer> gn = new ArrayList<>();
        // rn为镜像数据
        List<Integer> rn = new ArrayList<>();
        gn.add(0);
        rn.add(1);
        for (int i = 2; i < n; i++) {
            // 更新gn
            for (int j = 0; j < i; j++) {
                gn.addAll(rn);
            }
            // 更新rn
            for (int j = i - 1; j >= 0; j--) {
                // TODO
            }
        }

        // 最后的结果为gn和rn的组合
        gn.addAll(rn);
        return gn;
    }

    public List<Integer> grayCode(int n) {
        init(n);
        res.add(0);
        dfs(0);
        return new ArrayList<>(res);
    }

    private void dfs(int curCode) {
        if (res.size() == grayCodeNum) {
            return;
        }
        for (int mask : masks) {
            int nextCode = curCode ^ mask;
            if (!res.contains(nextCode)) {
                res.add(nextCode);
                dfs(nextCode);
            }
        }
    }

    private void init(int n) {
        masks = new int[n];
        for (int i = 0; i < n; i++) {
            masks[i] = 1 << i;
        }
        grayCodeNum = 1 << n;
    }

}
