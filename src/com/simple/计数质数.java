package com.simple;


/**
 * @author qinzhu
 * @since 2020/12/3
 *
 * 方式一：暴力
 * 方式二：埃式筛
 */
public class 计数质数 {
    public static void main(String[] args) {
        System.out.println(new 计数质数().countPrimes(499979));
    }

    /**
     * 使用埃式筛
     *      原理就是使用hash表存储所有的数，然后多次遍历hash表将质数的倍数都（比如当前的质数是11，那么就把11*2,11*3,11*4都标记为1）标记为1，
     *      最后未被标记的肯定就是质数了
     *
     * 此处可以优化的地方：如果当前的质数是x，其实不必从x * 2开始标记，而是从x * x开始标记，因为x * x之前的数肯定会被以前的质数标记
     * e.g.
     * x为5，直接从5 * 5开始标记（5 * 2被x为2的情况下标记了，5 * 3被x为3的情况下标记了，5 * 4倍x为2的情况下标记了）
     * x为7，直接从7 * 7开始标记（7 * 2倍x为2标记了，7 * 3被x为3标记了，.......7 * 6被x为2标记了）
     */
    public int countPrimes(int n) {
        byte[] hash = new byte[n];
        // 此处写按常理应该写for (long i = 2; i < n; i++) {
        // 但是可以剪枝操作for (long i = 2; i*i < n; i++) {
        for (long i = 2; i*i < n; i++) {
            for (long j = i*i; j < n; j += i) {
                hash[(int) j] = 1;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (hash[i] == 0) {
                count++;
            }
        }
        return count;
    }
}
