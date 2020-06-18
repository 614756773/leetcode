package sort;

import java.util.Arrays;

/**
 * @author qinzhu
 * @since 2020/6/18
 */
public class MergeSort {
    private int[] array;

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.array = new int[]{6, 1, 4, 7, 5, 7, 6, 3, 3};
        mergeSort.sort(0, mergeSort.array.length - 1);
        System.out.println(Arrays.toString(mergeSort.array));
    }

    public void sort(int left, int right) {
        if (left == right) {
            return;
        }

        int min = (left + right) / 2;
        sort(left, min);
        sort(min + 1, right);
        merge(left, right, min);
    }

    private void merge(int left, int right, int min) {
        int[] tmp = new int[right - left + 1];
        int i = 0;
        int leftIndex = left, rightIndex = min + 1;
        while (leftIndex <= min && rightIndex <= right) {
            if (array[leftIndex] < array[rightIndex]) {
                tmp[i++] = array[leftIndex++];
            } else {
                tmp[i++] = array[rightIndex++];
            }
        }
        while (leftIndex <= min) {
            tmp[i++] = array[leftIndex++];
        }
        while (rightIndex <= right) {
            tmp[i++] = array[rightIndex++];
        }
        for (int j = 0; j < tmp.length; j++) {
            array[left++] = tmp[j];
        }
    }
}
