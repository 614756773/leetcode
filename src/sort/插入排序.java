package sort;


/**
 * @author qinzhu
 * @since 2021/1/27
 */
public class 插入排序 {
    public static void main(String[] args) {
        int[] a = new int[]{6,1,4,7,5,6,7,7,3};
        sort(a);
        System.out.println(a);
    }

    private static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            int cur = a[j];
            while (j > 0 && cur < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = cur;
        }
    }
}
