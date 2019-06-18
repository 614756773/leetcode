package com.medium;

/**
 * @Date: 2019/6/18
 * @Author: qinzhu
 */
public class Z字形变换 {
    public static void main(String[] args) {
        String[] s = {"LEETCODEISHIRING", "", "LEETCODEISHIRING", "Stringconvert",
                        "ab"};
        int[] numRows = {1, 2, 3, 4};

        for (String s1 : s) {
            for (int numRow : numRows) {
                String result = convert(s1, numRow);
                System.out.println(result);
            }
        }
    }

    private static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int cols;
        int len = s.length();
        int index = 0;
        // 相邻的长短列为1组
        int groupSize = 2 * numRows - 2;
        int groupNumbers =len / groupSize;

        // 1.确定列数
        cols = groupNumbers * (numRows - 1);
        int r = len % groupSize;
        if (r != 0) {
            cols++;
            if (r > numRows) {
                int m = r % numRows;
                cols += m;
            }
        }
        // 2.按Z字形存放在数组中
        char[][] container = new char[numRows][cols];
        for (int col = 0; col < cols; col++) {
            // 用c来确定是否在普通竖列上，如果为0则是，否则是在斜线上
            int c = col % (numRows - 1);
            if (c == 0) {
                for (int row = 0; row < numRows; row++) {
                    if (index >= s.length()) {
                        break;
                    }
                    container[row][col] = s.charAt(index++);
                }
            } else {
                container[numRows - 1 - c][col] = s.charAt(index++);
            }
        }
        // 3.输出
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < container.length; row++) {
            char[] chars = container[row];
            for (int col = 0; col < chars.length; col++) {
                if (chars[col] != '\u0000') {
                    sb.append(chars[col]);
                }
            }
        }
        return sb.toString();
    }
}
