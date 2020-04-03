package com.medium;

/**
 * @since 2020-4-3 18:00:39
 * @author qinzhu
 */
public class 字符串转换整数 {
    public static void main(String[] args) {
        String[] s = new String[]{"   001", "a0bc123", " a0basd123", " -123ab0123",
                " 0123asd123", "", "  ", "abc",
                "61475677395", "-2147483648", "2147483647", "-",
                "+", "-6147483648", "2147483646", "-2147483647"};
        字符串转换整数 o = new 字符串转换整数();
        System.out.println(o.myAtoi("42"));
        for (String s1 : s) {
            System.out.println(s1);
            System.out.println(o.myAtoi(s1));
            System.out.println("_________________");
        }
    }

    // [48-57]
    private int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int index = 0;
        while (index < chars.length) {
            if (chars[index] == ' ') {
                index++;
            } else {
                break;
            }
        }
        if (index == chars.length) {
            return 0;
        }

        // 负数
        boolean isNegative = false;
        if (chars[index] == '-') {
            isNegative = true;
            index++;
        } else if (chars[index] == '+') {
            isNegative = false;
            index++;
        }

        int result = 0;
        while (index < chars.length) {
            char c = chars[index++];
            int num = c - 48;
            if (isNumber(c)) {
                if (result > (Integer.MAX_VALUE - num) / 10) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + num;
            } else {
                break;
            }
        }
        return isNegative ? -result : result;
    }

    private boolean isNumber(char c) {
        return c >= 48 && c <= 57;
    }
}
