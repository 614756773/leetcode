package com.simple;

/**
 * @Date: 2019/6/18
 * @Author: qinzhu
 */
public class 整数翻转 {
    public static void main(String[] args) {
        int[] nums = new int[]{1200, -120, 120, 0, 123, -123, (1 << 31) - 1, - (1 << 31), 1534236469, -1534236469};
        for (int num : nums) {
            System.out.println(num);
            System.out.println(reverse(num));
            System.out.println("--");
        }
    }

    private static int reverse(int x) {
        int xCopy = x;
        if (x > -10 && x < 10) {
            return x;
        }

        int[] container;
        // 负号会导致数组长1位
        if (x < 0) {
            container = new int[(x + "").length() - 1];
        } else {
            container = new int[(x + "").length()];
        }
        // 逆序放入数组
        for (int index = 0; x != 0; index++) {
            container[index] = x % 10;
            x = x / 10;
        }
        // 通过乘10，乘100....把数组合起来
        int result = 0;
        int tmp = 1;
        for (int i = container.length - 1; i >= 0; i--) {
            int m = container[i] * tmp;
            // 原数为正数时，翻转导致的溢出，m != 0这个条件是防止原数为1200或者1020这种情况
            if (xCopy > 0) {
                if (result + m < result || (m != 0 && m < tmp)) {
                    return 0;
                }
            // 原数为负数时，翻转导致的溢出
            } else {
                if (result + m > result || (m != 0 && -m < tmp)) {
                    return 0;
                }
            }
            result += m;
            tmp *= 10;
        }
        return result;
    }
}
