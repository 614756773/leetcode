package com.simple;

/**
 * @Date: 2019/6/19
 * @Author: qinzhu
 */
public class 回文数 {
    public static void main(String[] args) {
        int nums[] = new int[]{0, 1, 11, 121, 123,
                101, 0, 1231, -1231, -121,
                1221, 1225211, 1225221};
        for (int i : nums) {
            System.out.println(i);
            System.out.println(isPalindrome(i));
            System.out.println("--------------");
        }
        System.out.println(isPalindrome(1221));
    }

    private static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        // 确定x是几位数
        int numberDigits =(int)Math.log10(x) + 1;
        int limit = numberDigits / 2;
        for (int i = 0; i < limit; i++) {
            int pow = (int) Math.pow(10, numberDigits - 1);
            int right = x % 10;
            int left = x / pow;
            if (right != left) {
                return false;
            }
            // 左边和右边分别去掉1位
            x %= pow;
            x /= 10;
            numberDigits -= 2;
        }
        return true;
    }
}
