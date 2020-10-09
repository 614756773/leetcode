package com.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/10/9
 */
public class 格雷编码 {
    public static void main(String[] args) {
        System.out.println(new 格雷编码().grayCode(2));
        // TODO 结果有误
    }

    List<List<Integer>> res = new ArrayList<>();
    public List<Integer> grayCode(int n) {
        backtrack(0, n, new ArrayList<>());
        return convert(res);
    }

    /**
     * 将000,001,010...101之类的集合转换为0,1,2...5
     */
    private List<Integer> convert(List<List<Integer>> list) {
        List<Integer> result = new ArrayList<>();

        for (List<Integer> l : list) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : l) {
                sb.append(integer);
            }
            result.add(Integer.valueOf(sb.toString(), 2));
        }
        return result;
    }

    /**
     *
     * @param level 回溯的层级
     * @param n     结束条件
     * @param list  用于存储满足条件的数字及其序列，后续会转换成格雷码
     */
    private void backtrack(int level, int n, List<Integer> list) {
        if (level == n) {
            if (res.isEmpty()) {
                res.add(new ArrayList<>(list));
                return;
            }

            // 比较与上一个格雷码是否只有1位不同
            List<Integer> preGrayCode = res.get(res.size() - 1);
            if (onlyOneBitDifferent(preGrayCode, list)) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = 0; i <= 1; i++) {
            list.add(i);
            backtrack(level + 1, n, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 是否只有一bit不同
     */
    private boolean onlyOneBitDifferent(List<Integer> preGrayCode, List<Integer> list) {
        int diffCount = 0;
        for (int i = 0; i < preGrayCode.size(); i++) {
            if (!preGrayCode.get(i).equals(list.get(i))) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
