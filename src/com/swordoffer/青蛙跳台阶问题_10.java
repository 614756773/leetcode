package com.swordoffer;

/**
 * @author: qinzhu
 * @since: 2020/11/22
 * 思路：动态规划
 *      设f(n)为跳到第n个台阶的跳法种树，于是可以得到以下初始条件
 *          f(0) = 1       默认值，只有一种跳发那就是不动。。
 *          f(1) = 1       从第0个台阶调到第一个台阶只有一种跳发，就是跳1次
 *      递推公式：f(n) = f(n-1) + f(n-2)
 *              很好理解嘛，跳到当前台阶的跳发只有两种情况，要么从前一个台阶跳一步，要么从前两个台阶跳两步
 */
public class 青蛙跳台阶问题_10 {
    public int numWays(int n) {
        if (n < 2) {
            return 1;
        }

        int[] memory = new int[n + 1];
        memory[0] = 1;
        memory[1] = 1;
        for (int i = 2; i <= n; i++) {
            memory[i] = (memory[i - 1] % 1000000007) + (memory[i - 2] % 1000000007);
        }
        return memory[n] % 1000000007;
    }
}
