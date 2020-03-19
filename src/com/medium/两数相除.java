package com.medium;

/**
 * @author qinzhu
 * @since 2020/3/18
 * 核心思想就是二分，比如31/3
 * 3 << 1 小于31， 3 << 2 还是小于31， 3 << 3 还是小于31， 3 << 4 大于31
 * 所以我们知道当3扩大8倍时小于31，扩大16倍时大于31，所以结果在[8,31)范围内，
 * 然后递归计算7/3，7为余下的数
 */
public class 两数相除 {
    public static void main(String[] args) {
        System.out.println(new 两数相除().divide(7, 2));
        System.out.println(new 两数相除().divide(15, 2));
        System.out.println(new 两数相除().divide(2147483647, 2));
        System.out.println(new 两数相除().divide(7, 3));
        System.out.println(new 两数相除().divide(10, 3));
        System.out.println(new 两数相除().divide(-31, -3));

    }

    public int divide(int dividend, int divisor) {
        int filp = 1;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            filp = -1;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        }

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        if (b > a) {
            return 0;
        }

        return compute(a, b) * filp;
    }

    /**
     *  @param a 被除数
     * @param b 除数
     */
    private int compute(long a, long b) {
        if (a < b) {
            return 0;
        }
        int i = 1;
        long bigB;
        while (true) {
            bigB = b << i;
            if (bigB > a) {
                break;
            }
            i++;
        }
        // 当i等于1时，就说明最终结果再加1就够了，然后也不用再递归下去了
        if (i == 1) {
            return 1;
        }

        int result = 1 << (i - 1);
        return result + compute(a - (bigB >> 1), b);
    }
}
