package com.medium;

public class 排序数组 {
    public static void main(String[] args) {
        int[] result = new 排序数组().sortArray(new int[]{6, 0, -8, -1, -100,0,65});
        System.out.println(result);
    }

    public int[] sortArray(int[] nums) {
        // 正数hash
        short[] positiveHash = new short[50001];
        // 负数hash
        short[] negativeHash = new short[50000];
        for (int num : nums) {
            if (num >= 0) {
                positiveHash[num]++;
            } else {
                negativeHash[-num]++;
            }
        }

        int[] result = new int[nums.length];
        int cur = 0;
        for (int i = 0; i < positiveHash.length; i++) {
            while (positiveHash[i] != 0) {
                result[cur++] = i;
                positiveHash[i]--;
            }
        }

        int mid = cur;
        for (int i = negativeHash.length - 1; i >= 1; i--) {
            while (negativeHash[i] != 0) {
                result[cur++] = -i;
                negativeHash[i]--;
            }
        }
        // 把后半截的负数移动到前面去
        int[] tmp = new int[mid];
        System.arraycopy(result, 0, tmp, 0, mid);
        System.arraycopy(result, mid, result, 0, nums.length - mid);
        System.arraycopy(tmp, 0, result, nums.length - mid, tmp.length);
        return result;
    }
}
