package com.medium;

/**
 * @author hotpot
 * @since 2020-04-19 11:43:40
 * 思路：
 * 有点像俄罗斯方块，先把croa都记录，当遇到k时就把croa都减一，当最后再判断croa是否都为0，都为0了说明就是`正常的青蛙叫声`
 * 细节：
 *  a.  要保证c >= r >= o >= a
 *  b.  同时存在c的最大数量就是青蛙的数量（但是需要在结束时判断croa是否都为0了）
 */
public class 数青蛙 {
    public static void main(String[] args) {
        System.out.println(new 数青蛙().minNumberOfFrogs("crocakcroraoakk"));
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        char[] chars = croakOfFrogs.toCharArray();
        if (chars.length < 5 || chars[chars.length - 1] != 'k') {
            return -1;
        }

        int maxFrogs = 0;
        int c = 0, r = 0, o = 0, a = 0;
        for (char ch : chars) {
            switch (ch) {
                case 'c':
                    c++;
                    break;
                case 'r':
                    r++;
                    if (r > c) {
                        return -1;
                    }
                    break;
                case 'o':
                    o++;
                    if (o > r) {
                        return -1;
                    }
                    break;
                case 'a':
                    a++;
                    if (a > o) {
                        return -1;
                    }
                    break;
                case 'k':
                    maxFrogs = Math.max(maxFrogs, c);
                    c--;r--;o--;a--;
                    break;
            }
        }

        // 其实应该判断c,r,o,a的值都为0，但是因为他们不可能为负数，所以直接判断和为0即可
        if (c + r + o + a > 0) {
            return -1;
        }
        return maxFrogs;
    }
}
