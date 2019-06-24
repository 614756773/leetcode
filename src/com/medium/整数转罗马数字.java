package com.medium;

/**
 * @Date: 2019/6/21
 * @Author: qinzhu
 */
public class 整数转罗马数字 {
    public static void main(String[] args) {
//        int[] nums = new int[]{3, 4, 9, 58, 1994};
//        for (int num : nums) {
//            System.out.println(num);
//            System.out.println(intToRoman(num));
//            System.out.println("______________________");
//        }
        System.out.println(intToRoman(3300));
        System.out.println(intToRoman(588));
    }

    private static String intToRoman(int num) {
        char[] roman = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        StringBuilder result = new StringBuilder();
        // 千位
        if (num / 1000 > 0) {
            result.append(getStr(num / 1000, roman.length - 1, roman));
        }
        // 百位
        int tmp = num % 1000 / 100;
        if (tmp == 9) {
            result.append("CM");
        }else if (tmp == 4) {
            result.append("CD");
        } else {
            result.append(getStr(tmp, roman.length - 3, roman));
        }
        // 十位
        tmp = num % 100 / 10;
        if (tmp == 9) {
            result.append("XC");
        } else if (tmp == 4) {
            result.append("XL");
        } else {
            result.append(getStr(tmp, 2, roman));
        }
        // 个位
        tmp = num % 10;
        if (tmp == 9) {
            result.append("IX");
        } else if (tmp == 4) {
            result.append("IV");
        } else {
            result.append(getStr(tmp, 0, roman));
        }
        return result.toString();
    }

    private static StringBuilder getStr(int tmp, int romanIndex, char[] roman) {
        StringBuilder sb = new StringBuilder();
        if (tmp >= 5 ) {
            sb.append(roman[romanIndex + 1]);
            tmp -= 5;
        }
        for (int i = 0; i < tmp; i++) {
            sb.append(roman[romanIndex]);
        }
        return sb;
    }
}
