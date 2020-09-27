package com.swordoffer;

/**
 * @author: qinzhu
 * @since: 2020/09/27
 * 思路：
 * 1.使用fa，fb记录前两个数列
 * 2.数学公式：设正整数 x,y,p ，求余符号为 % ，则有 (x + y) % p = ((x % p) + (y % p)) % p
 */
public class 斐波那契数列_10 {
    public static void main(String[] args) {
        for (int i = 0; i < 56; i++) {
            System.out.println(new 斐波那契数列_10().fib(i));
        }
    }
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int res = -1;
        int fa = 0;
        int fb = 1;

        for (int i = 2; i <= n; i++) {
            res = (fa + fb) % 1000000007;
            fa = fb;
            fb = res;
        }
        return res;
    }
}
