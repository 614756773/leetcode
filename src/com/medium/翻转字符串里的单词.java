package com.medium;

/**
 * @author qinzhu
 * @since 2020/4/10
 * 思路：
 * 1.先去掉头尾的空格，找到开始和结束位置
 * 2.从结束位置往前遍历，记录每一组（这儿的一组就是指一个单词）的perEnd，当遇到空格，则说明有一个单词了，把这个单词存入到结果集
 * 3.由于第一个单词的前面是没有空格的所以单独把第一个单词存入到结果集
 */
public class 翻转字符串里的单词 {

    public static void main(String[] args) {
        System.out.println(new 翻转字符串里的单词().reverseWords(" 123 456 789 "));
    }

    public String reverseWords(String s) {
        if (s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int begin = findBegin(chars);
        int end = findEnd(chars);
        // 本来该是end - begin + 1，但是最后会多一个空格，然后要移除掉
        char[] ans = new char[end - begin + 2];
        int ansIndex = 0;

        int perEnd = end;
        for (int i = end; i >= begin ; i--) {
            if (chars[perEnd] == ' ') {
                perEnd--;
                continue;
            }
            if (chars[i] == ' ') {
                // 添加到结果集中
                for (int j = i + 1; j <= perEnd; j++) {
                    ans[ansIndex++] = chars[j];
                }
                ans[ansIndex++] = ' ';
                perEnd = i - 1;
            }
        }

        // 由于上面的循环是通过每个单词前面有空格来进行判断的，第一个单词的前面是没有空格的
        // 所以单独把第一个单词加上
        for (int i = begin; i <= end; i++) {
            if (chars[i] == ' ') {
                break;
            }
            ans[ansIndex++] = chars[i];
        }

        return String.valueOf(ans, 0, ansIndex);
    }

    private int findEnd(char[] chars) {
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != ' ') {
                return i;
            }
        }
        return 0;
    }

    private int findBegin(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                return i;
            }
        }
        return 0;
    }

}
