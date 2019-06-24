package com.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/6/24
 * @Author: qinzhu
 */
public class 罗马数字转整数 {

    public static void main(String[] args) {
        String[] str = new String[]{"MCMXCIV", "LVIII", "IX", "IV", "III"};
        for (String s : str) {
            System.out.println(romanToInt(s));
        }
        System.out.println(romanToInt("MCMXCIV"));
    }

    private static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        char[] chars = s.toCharArray();
        int pre = 1000;
        for (char c : chars) {
            Integer current = map.get(c);
            result += current;
            if (current > pre) {
                result = result - (2 * pre);
            }
            pre = current;
        }
        return result;
    }
}
