package com.simple;

/**
 * @author qinzhu
 * @since 2020/11/19
 * 思路： 一次迭代
 * 1. 进行迭代，并在迭代的过程中使用变量zeroCount记录0的个数
 * 2. 若当前元素不为0且zeroCount的值大于0，那么就说明当前元素需要往前移动，并且移动zeroCount步
 * 3. 迭代完毕后，将原数组的末尾替换为0，需要替换zeroCount个元素
 */
public class 移动零 {
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                zeroCount++;
                continue;
            }

            if (zeroCount > 0) {
                nums[i - zeroCount] = num;
            }
        }

        for (int i = nums.length - 1; i >= 0 && zeroCount > 0; i--, zeroCount--) {
            nums[i] = 0;
        }
    }
}
