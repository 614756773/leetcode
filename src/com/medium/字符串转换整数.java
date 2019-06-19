package com.medium;

/**
 * @Date: 2019/6/18
 * @Author: qinzhu
 */
public class 字符串转换整数 {
    public static void main(String[] args) {
        String[] s = new String[]{"   001", "a0bc123", " a0basd123", " -123ab0123",
                " 0123asd123", "", "  ","abc",
                "61475677395", "-2147483648", "2147483647", "-",
                "+", "-6147483648", "2147483646", "-2147483647"};
        for (String s1 : s) {
            System.out.println(s1);
            System.out.println(myAtoi(s1));
            System.out.println("_________________");
        }
//        System.out.println(myAtoi("-6147483648"));
    }

    // [48-57]
    private static int myAtoi(String str) {
        str = str.trim();
        if (str.equals("")) {
            return 0;
        }
        boolean isNegative = false;
        // 负数
        if (str.charAt(0) == '-') {
            isNegative = true;
            str = str.substring(1);
        } else if(str.charAt(0) == '+') {
            str = str.substring(1);
        }
        if (str.equals("")) {
            return 0;
        }

        int index = 0;
        int result = 0;
        while (index < str.length()) {
            char c = str.charAt(index++);
            if (c >= 48 && c <= 57) {
                if (result > Integer.MAX_VALUE / 10) {
                    return isNegative ? -(1 << 31) : (1 << 31) -1;
                }
                if ((result * 10) > Integer.MAX_VALUE - (c - 48)) {
                    return isNegative ? -(1 << 31) : (1 << 31) -1;
                }
                result = result * 10 + (c - 48);
            }
            else {
                break;
            }
        }
        return isNegative ? -result : result;
    }
}
