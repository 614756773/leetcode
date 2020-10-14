package com.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinzhu
 * @since 2020/10/14
 * 题目：
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（不包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 * 输入：["bella","label","roller"]
 * 输出：["e","l"]
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 *
 *
 * 思路：hash
 *      简单点的操作就是有n个字符串就创建n个数组，然后遍历这n个字符串将它们拥有的字符放各自的数组中，
 *      最后将这n个数组中都存在的字符取出来
 *
 *      进阶版本则是使用位操作，用n个int来代替n个数组（因为int有32位，而小写字符一共只有26个，32 > 26，用来表示26个字母还有多余），
 *      最后将这n个int进行&操作，剩下的就是结果了。这样做的好处即节省了空间，还使用&操作避免了迭代n个数组进行if比较
 */
public class 查找常用字符_自创题目 {
    private int[] masks = new int[26];

    public static void main(String[] args) {
        查找常用字符_自创题目 a = new 查找常用字符_自创题目();
        System.out.println(a.commonChars(new String[]{"abc", "adddbbbbbd", "bac"}));
    }

    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if (A == null || A.length == 0) {
            return res;
        }

        initMasks(masks);

        int[] hash = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            String str = A[i];
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                hash[i] |= masks[ch - 'a'];
            }
        }

        // v的初始二进制全设置为1，即1111111111111111111111111111111
        int v = Integer.MAX_VALUE;
        for (int tmp : hash) {
            v &= tmp;
        }

        // 如果v最后的值为0000000000000000000000000000111，那么就说明常用字符有a,b,c
        // 如果v最后的值为0000000000000000000000000000100，那么就说明常用字符有c
        // 如果v最后的值为0000000000000000000000001000100，那么就说明常用字符有c,g
        for (int i = 0; i < masks.length; i++) {
            int chBit = masks[i] & v;
            if (chBit != 0) {
                res.add(String.valueOf((char) ('a' + i)));
            }
        }

        return res;
    }

    private void initMasks(int[] masks) {
        for (int i = 0; i < masks.length; i++) {
            masks[i] = 1 << i;
        }
    }
}
