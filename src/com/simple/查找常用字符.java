package com.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/10/14
 * 思路：hash
 * 有n个字符串就创建n个hash桶，每个hash桶可以容纳26个元素（因为小写字符刚好26个），
 * 迭代字符串将它们的字符数统计在自己的hash桶中，
 * 最后将所有hash桶的同一位置都有值的数据作为结果（并且取最小的重复次数来进行重复生成结果）
 */
public class 查找常用字符 {
    public static void main(String[] args) {
        System.out.println(new 查找常用字符().commonChars(new String[]{"abbc", "abbbc"}));
    }
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if (A == null || A.length == 0) {
            return res;
        }

        byte[][] hashTable = new byte[A.length][];
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new byte[26];
        }

        for (int i = 0; i < A.length; i++) {
            String str = A[i];
            byte[] hash = hashTable[i];
            for (int j = 0; j < str.length(); j++) {
                hash[str.charAt(j) - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            int count = Integer.MAX_VALUE;
            boolean isSuccess = true;
            for (byte[] hash : hashTable) {
                if (hash[i] == 0) {
                    isSuccess = false;
                    break;
                }
                count = Math.min(count, hash[i]);
            }

            if (isSuccess) {
                // 生成count次该字符
                for (int j = 0; j < count; j++) {
                    res.add(String.valueOf((char)('a' + i)));
                }
            }
        }
        return res;
    }
}
