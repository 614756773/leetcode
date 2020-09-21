package com.swordoffer;


/**
 * @author: hotpot
 * @since: 2020/09/21
 * 思路：先算出空格的数量，然后计算出新字符串的长度，接着倒序拷贝
 */
public class 替换空格_4 {
    public String replaceSpace(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }

        String replace = "%20";
        char[] chars = s.toCharArray();
        int numberOfSpaces = 0;
        for (char c : chars) {
            if (c == ' ') {
                numberOfSpaces++;
            }
        }

        if (numberOfSpaces == 0) {
            return s;
        }

        char[] result = new char[s.length() + 2 * numberOfSpaces];
        int j = result.length - 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                result[j--] = '0';
                result[j--] = '2';
                result[j--] = '%';
            } else {
                result[j--] = chars[i];
            }
        }
        return new String(result);
    }
}
