package com.simple;

/**
 * @author hotpot
 * @since 2020-04-19 10:44:10
 * 思路：
 * 1.把数字和字符分别存储在nums数组和chars数组中
 * 2.然后交替取nums和chars中的元素用于生成结果
 * 涉及到一些细节问题：
 *  a. 如果两者的元素个数相差大于2则不满住要求，返回-1
 *  b. 如果nums有2个元素，chars有3个元素，那么应该先访问chars。反之亦然
 */
public class 重新格式化字符串 {
    public static void main(String[] args) {
        System.out.println(new 重新格式化字符串().reformat("j"));
    }
    public String reformat(String s) {
        int len = s.length();
        if(len == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        char[] nums = new char[len];
        char[] chars = new char[len];
        int numCur = 0;
        int charCur = 0;
        char[] all = s.toCharArray();

        for (int i = 0; i < all.length; i++) {
            if (numCur > nums.length || charCur > chars.length) {
                return "";
            }

            char c = all[i];
            if (c <= 57) {
                nums[numCur++] = c;
            } else {
                chars[charCur++] = c;
            }
        }

        if (Math.abs(charCur - numCur) >= 2) {
            return "";
        }

        if (charCur >= numCur) {
            charCur = 0;
            numCur = 0;
            while (true) {
                char a = chars[charCur++];
                if (a == '\u0000') {
                    break;
                }
                sb.append(a);
                char b = nums[numCur++];
                if (b == '\u0000') {
                    break;
                }
                sb.append(b);
            }
        } else {
            charCur = 0;
            numCur = 0;
            while (true) {
                char b = nums[numCur++];
                if (b == '\u0000') {
                    break;
                }
                sb.append(b);
                char a = chars[charCur++];
                if (a == '\u0000') {
                    break;
                }
                sb.append(a);
            }
        }

        while (charCur < chars.length) {
            char a = chars[charCur++];
            if (a == '\u0000') {
                break;
            }
            sb.append(a);
        }
        while (numCur < nums.length) {
            char b = nums[numCur++];
            if (b == '\u0000') {
                break;
            }
            sb.append(b);
        }
        return sb.toString();
    }
}
