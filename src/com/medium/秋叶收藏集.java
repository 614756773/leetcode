package com.medium;

/**
 * @author: qinzhu
 * @since: 2020/10/01
 * 思路动态规划：
 * 定义dp[i][j]表示为从第0片叶子到第i片叶子变为某个颜色的最小操作步数
 * 其中i表示第几片叶子，
 * j表示叶子的状态，在题目中叶子一共有3中状态：
 *      第一种，叶子为红色并且是在黄色叶子左侧的     定义为 j = 0
 *      第二种，叶子为黄色，夹在红色叶子之间         定义为 j = 1
 *      第三种，叶子为红色并且是在黄色叶子右侧的      定义为 j = 2
 *
 * dp[0][0]等于1或者0    表示第0片叶子为左侧红色叶子，可能需要操作1次或者不用操作
 * dp[0][1]等于正无穷    表示第0片叶子为中间黄色叶子，需要操作无数次（因为压根就不可以，所以就定义成无穷了）
 * dp[0][2]等于正无穷    表示第0片叶子为右侧红色叶子，需要操作无数次（理由同上）
 *
 * dp[i][0] = dp[i-1][0] + isYellow(i)  第i片叶子状态为0的情况，需要操作的步数为第i-1片叶子状态为0的步数 + isYellow(i)。如果第i片叶子原本为黄色，则isYellow(i)值为1，否则为0
 * dp[i][1] = min{dp[i-1][0], dp[i-1][1]} + isRead(i)   如果第i片叶子原本为红色，则isRead(i)值为1，否则为0
 * dp[i][2] = min{dp[i-1][1], dp[i-1][2]} + isYellow(i)
 *
 * 我们要的最终结果肯定就是dp[i][2]这个状态
 */
public class 秋叶收藏集 {

    public int minimumOperations(String leaves) {
        char[] chars = leaves.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            // 初始递推条件的生成
            if (i == 0) {
                dp[i][0] = isYellow(chars[i]);
                // 这儿的n其实就相当于是无穷的意思，但是不能使用Integer.MAX_VALUE，因为后面可能会+1导致溢出。。
                // 而使用n基本上就不会溢出，况且总共有n个字符，最多操作n次就能得到满足条件的结果，所以就用n代替最大值了
                dp[i][1] = n;
                dp[i][2] = n;
                continue;
            }

            // 主要的递推过程
            dp[i][0] = dp[i - 1][0] + isYellow(chars[i]);
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRead(chars[i]);
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow(chars[i]);
        }

        return dp[n - 1][2];
    }

    private int isYellow(char ch) {
        return ch == 'y' ? 1 : 0;
    }

    private int isRead(char ch) {
        return ch == 'r' ? 1 : 0;
    }
}
