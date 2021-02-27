package com.medium.递归;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hotpot
 * @since: 2021/02/27
 */
public class 至少有K个重复字符的最长子串 {
    public static void main(String[] args) {
        至少有K个重复字符的最长子串 o = new 至少有K个重复字符的最长子串();
        System.out.println(o.longestSubstring("aaabb", 4));
    }

    public int longestSubstring(String s, int k) {
        // base case
        if (s.length() < k) {
            return 0;
        }

        Map<Character, Integer> map = countTheNumberOfCharacters(s);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.get(ch) < k) {
                String[] split = s.split(String.valueOf(ch));
                int res = 0;
                for (String subString : split) {
                    res = Math.max(res, longestSubstring(subString, k));
                }
                return res;
            }
        }
        return s.length();
    }

    private Map<Character, Integer> countTheNumberOfCharacters(String s) {
        Map<Character, Integer> map = new HashMap<>(16);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }
}
