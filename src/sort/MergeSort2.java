package sort;

import java.util.Arrays;

/**
 * @author: qinzhu
 * @since: 2020/11/21
 */
public class MergeSort2 {
    private int[] array;

    public static void main(String[] args) {
        MergeSort2 mergeSort2 = new MergeSort2();
        mergeSort2.array = new int[]{6, 1, 4, 7, 5, 7, 6, 3, 3};
        mergeSort2.sort(0, mergeSort2.array.length - 1);
        System.out.println(Arrays.toString(mergeSort2.array));
    }

    public void sort(int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        // 1.分割
        sort(left, mid);
        sort(mid + 1, right);

        // 2.归并
        int i = left;
        int j = mid + 1;
        int cur = 0;
        int[] tmpArray = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (array[i] < array[j]) {
                tmpArray[cur++] = array[i];
                i++;
            } else {
                tmpArray[cur++] = array[j];
                j++;
            }
        }
        while (i <= mid) {
            tmpArray[cur++] = array[i++];
        }
        while (j <= right) {
            tmpArray[cur++] = array[j++];
        }

        cur = 0;
        for (int k = left; k <= right; k++) {
            array[k] = tmpArray[cur++];
        }
    }
}
