package com.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2020/12/4
 * 思路：贪心
 *      把这道题理解为打牌就很容易了，题目就是要求我们把手中的牌凑成“顺子”，并且“顺子”的最小长度为3
 *      先凑出一个最小的顺子，然后看下个数字的张牌，
 *          如果下一个数字的牌不存在则跳过，否则
 *              如果下一个数字的牌能够和之前的顺子凑成新的顺子，那么就加入（比如之前是123，新的元素是4）
 *              否则找后面连续两个数字的牌凑成顺子，如果能凑成则继续，如果不能凑成则直接返回false
 */
public class 分割数组为连续子序列 {
    public static void main(String[] args) {
        System.out.println(new 分割数组为连续子序列().isPossible(new int[]{1,2,3,3,4,4,5,5}));
    }

    public boolean isPossible(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        // 存储剩余的元素及其数量，key -> 剩余的元素值， value -> 剩余的元素数量
        Map<Integer, Integer> countMap = new HashMap<>();
        // 存储凑成的顺子，比如该Map的key里面有3和5，表示有1,2,3顺子和1,2,3,4,5顺子。 value则表示当前key的顺子有几个
        HashMap<Integer, Integer> straightMap = new HashMap<>();

        for (int num : nums) {
            Integer count = countMap.getOrDefault(num, 0);
            countMap.put(num, ++count);
        }

        for (int num : nums) {
            Integer count = countMap.get(num);
            if (count == 0) {
                continue;
            }
            
            countMap.put(num, --count);

            // 情况a：能够和之前的顺子凑在一起
            Integer straightCount = straightMap.getOrDefault(num - 1, 0);
            if (straightCount > 0) {
                straightMap.put(num - 1, straightCount - 1);
                straightMap.put(num, straightMap.getOrDefault(num, 0) + 1);
                continue;
            }

            // 情况b：能够与后面的元素凑成一个最小的顺子（即长度为3）
            Integer theSecond = countMap.getOrDefault(num + 1, 0);
            Integer theThird = countMap.getOrDefault(num + 2, 0);
            if (theSecond > 0 && theThird > 0) {
                int count_ = straightMap.getOrDefault(num + 2, 0) + 1;
                straightMap.put(num + 2, count_);
                countMap.put(num + 1, theSecond - 1);
                countMap.put(num + 2, theThird - 1);
                continue;
            }

            // 情况c：a、b都不满足，说明这手牌不能搞出全是顺子
            return false;
        }

        return true;
    }
}
