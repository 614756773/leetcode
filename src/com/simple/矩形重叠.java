package com.simple;

/**
 * @author qinzhu
 * @since 2020/3/18
 * 解题思路：反向判断矩形不重叠
 */
public class 矩形重叠 {
    public static void main(String[] args) {
        System.out.println(new 矩形重叠().isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
        System.out.println("-----------------");
        System.out.println(new 矩形重叠().isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
        System.out.println("-----------------");
        System.out.println(new 矩形重叠().isRectangleOverlap(new int[]{4, 0, 6, 6}, new int[]{-5, -3, 4, 2}));
    }

    /**
     * 假设的顺序分别是：a在b左侧，a在b右侧，a在b下方，a在b上方
     */
    public boolean isRectangleOverlap(int[] a, int[] b) {
        return !((a[2] <= b[0] || a[0] >= b[2]) || (a[3] <= b[1] || a[1] >= b[3]));
    }
}
