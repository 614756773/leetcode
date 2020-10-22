package com.medium;

import java.util.*;

/**
 * @author qinzhu
 * @since 2020/10/22
 * 思路：双指针 + 贪心
 * e.g.
 * 处理字符abcabefg，
 * 首先计算出各字符的最末位置：a为3，b为4，c为3，e为5，f为6，g为7
 * 然后使用start和end双指针迭代字符，
 * 迭代至a时，end为a的最末端，也就是3，
 * 迭代至ab时，end更新为b的最末端，也就是4
 * 迭代至abc时，end不更新（因为c的最末端为2，比之前的end 4更小）
 * 迭代至abca时，end不更新（因为a的最末端为3，比之前的end 4更小）
 * 迭代至abcab时，此时索引为4，而且end也为4，说明已经是一个合格的子串了。于是计算出该子串长度加入结果集，并且更新start的值
 * 继续。。。。。。。。。。。。。
 */
public class 划分字母区间 {
    public static void main(String[] args) {
        String s = "abcabefg";
        System.out.println(s.length());
        System.out.println(new 划分字母区间().partitionLabels_(s));
    }    public List<Integer> partitionLabels_(String S) {
        ArrayList<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) {
            return res;
        }
        // 1.计算出各字符最末端的位置
        int[] endPosition = new int[26];
        for (int i = 0; i < S.length(); i++) {
            endPosition[S.charAt(i) - 'a'] = i;
        }

        // 2.使用`双指针` + `贪心`计算结果
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            // 在迭代过程中，end指针是在不断变大的，这儿就是贪心的体现
            end = Math.max(end, endPosition[S.charAt(i) - 'a']);
            // 当迭代的位置和end重合，说明这儿已经是一个合格的子串了
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }


    /**
     * 思路：贪心，模拟操作
     * 需要记住前面已经出现过哪些字母
     * 需要记住这些字母在哪个子串中
     * 动态更新子串，也就是合并
     */
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        LinkedList<Dto> preStrings = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            // 倒序遍历以前的子串
            boolean find = false;
            for (int j = preStrings.size() - 1; j >= 0; j--) {
                // 前一个子串包含新的字符，则加入之前的子串中
                Dto dto = preStrings.get(j);
                if (dto.contains(ch)) {
                    find = true;
                    // 合并[j,size - 1]的子串
                    if (j == preStrings.size() - 1) {
                        // 如果是在最后一个子串发现有相同的，直接加入
                        preStrings.getLast().add(ch);
                    } else {
                        // 否则要把子串进行合并
                        Dto newDto = new Dto();
                        newDto.add(ch);
                        int size = preStrings.size();
                        for (int k = j; k < size; k++) {
                            Dto last = preStrings.removeLast();
                            newDto.merge(last);
                        }
                        preStrings.addLast(newDto);
                    }
                    break;
                }
            }

            // 没找到则新生成子串
            if (!find) {
                Dto newDto = new Dto();
                newDto.add(ch);
                preStrings.add(newDto);
            }
        }
        return convert(preStrings);
    }

    private List<Integer> convert(LinkedList<Dto> preStrings) {
        List<Integer> res = new ArrayList<>(preStrings.size());
        preStrings.forEach(e -> {
            res.add(e.num);
        });
        return res;
    }

    static class Dto {
        // 该子串拥有的字符数
        int num = 0;
        // 该子串拥有的字符类型
        Set<Character> set = new HashSet<>();
        void add(Character character) {
            set.add(character);
            num++;
        }

        boolean contains(Character character) {
            return set.contains(character);
        }

        // 合并两个子串
        public void merge(Dto e) {
            num += e.num;
            set.addAll(e.set);
        }
    }
}
