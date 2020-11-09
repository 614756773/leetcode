package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author qinzhu
 * @since 2020/11/9
 * 比 {@link FastSort} while内部少一次交换的快排
 */
public class FastSort_2 {
    private static int[] a = new int[10];

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            test();
            System.out.println(Arrays.toString(a));
            sort(0, a.length - 1);
            System.out.println(Arrays.toString(a));
            System.out.println("----------");
        }
    }

    private static void test() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(30);
        }
    }

    public static void sort(int low, int high) {
        if (low >= high) {
            return;
        }

        // 使用key来存储基准值
        int key = a[low];
        int left = low;
        int right = high;
        while (left < right) {
            while (a[right] >= key && right > left) {
                right--;
            }
            while (a[left] <= key && right > left) {
                left++;
            }
            if (right > left) {
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
            }
        }
        // 基准复位
        a[low] = a[left];
        a[left] = key;

        // 分治处理
        sort(low, left - 1);
        sort(left + 1, high);
    }
}
