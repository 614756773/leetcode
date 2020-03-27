package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/27
 * 思路：使用hash统计各数字的次数，然后再判断两两之间的次数是否有公约数
 */
public class 卡牌分组 {
    public static void main(String[] args) {
        boolean b = new 卡牌分组().hasGroupsSizeX(new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3});
        System.out.println(b);
    }

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null) {
            return false;
        }
        if (deck.length <= 1) {
            return false;
        }

        int[] hash = new int[10000];
        for (int v : deck) {
            hash[v]++;
        }

        for (int i = 0; i < hash.length; i++) {
            int a = hash[i];
            if (a == 0) {
                continue;
            }
            for (int j = 0; j < hash.length; j++) {
                int b = hash[j];
                if (b == 0) {
                    continue;
                }
                if (!commonDivisor(a, b)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 是否存在不为1的公约数
     */
    private boolean commonDivisor(int a, int b) {
        if (a < 2 || b < 2) {
            return false;
        }
        if (a == b) {
            return true;
        }
        for (int i = 2; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                return true;
            }
        }
        return false;
    }

}
