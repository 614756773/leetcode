package com.medium;

/**
 * @author hotpot
 * @since 2020-05-11 22:35:56
 * 思路：使用二分法，比如求2^64，就可以变为计算（2^32）*（2^32）
 * 注意点：
 *      1.最需要需要需要需要注意的地方！！！！！！，递归返回的时候避免重复计算，比如我最开始写的是：
 *          private double quicklyPow(double x, long n) {
 *              xxxxxx
 *              return quicklyPow(x, n / 2) * quicklyPow(x, n / 2);
 *          }
 *          应该改成
 *          private double quicklyPow(double x, long n) {
 *              xxxxxx
 *              double v = quicklyPow(x, n / 2);
 *              return v * v;
 *          }
 *          可以省下超级多的调用层次，远不止节省了1倍开销！
 *      2.计算x^-n等于计算(1/x)^n，要注意n可能为-2147483648,如果直接对n取反，会溢出的，所以要用long来存储n然后取反
 */
public class 题目号50求次幂 {
    public static void main(String[] args) {
        System.out.println(new 题目号50求次幂().myPow(1, -2147483648));
    }

    public double myPow(double x, int n) {
        if (x == 0) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        long N = n;
        if(N < 0) {
            x = 1 / x;
            N = -N;
        }
        return quicklyPow(x, N);
    }

    private double quicklyPow(double x, long n) {
        if (n == 1) {
            return x;
        }
        long half = n / 2;
        double v = quicklyPow(x, half);
        if (n % 2 == 0) {
            return v * v;
        }
        return v * v * x;
    }
}
