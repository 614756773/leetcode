package sort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FastSort {

    private static int[] a = {1, 1, 3, 8, 7, 3, 2, 0, 4, 1};

    public static void main(String[] args) {
        System.out.println(1/2);
        sort(0, a.length - 1);
        System.out.println(Stream.of(a).map(Arrays::toString).collect(Collectors.joining(",")));
    }

    private static void sort(int low, int high) {
        //这个判断很重要 不然排好序后还会一直递归调用sort函数
        if (low >= high) {
            return;
        }
        //记录当前分组的最左侧和最右侧
        int left = low;
        int right = high;
        //用于比较的关键值
        int key = a[low];
        int tmp;
        while (low < high) {
            //把比关键字大的放右边
            while (high > low && a[high] >= key) {
                high--;
            }
            if (low < high) {
                tmp = a[low];
                a[low] = a[high];
                a[high] = tmp;
            }
            //把比关键字小的放左边
            while (low < high && a[low] <= key) {
                low++;
            }
            if (low < high) {
                tmp = a[low];
                a[low] = a[high];
                a[high] = tmp;
            }
        }
        //分成左右两组，继续比较
        sort(left, high - 1);
        sort(high + 1, right);
    }
}