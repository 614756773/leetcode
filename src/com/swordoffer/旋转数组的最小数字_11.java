package com.swordoffer;

/**
 * @author: qinzhu
 * @since: 2020/11/22
 * 思路: 二分法
 * 因为题目的数据是旋转过的升序数组，所以可以视为左右两侧两个升序数组，所以目标点就在右侧升序数组的第一个位置。
 * 使用index1、index2两个指针（index1一定是左侧数组中的，index2一定是右侧数组中的），找到index1和index2的中点midIndex，
 * 如果中点的值大于等于index1的值，那么中点肯定是在左侧的升序数组中，那么目标点肯定在右边，于是令index1=midIndex，
 * 如果中点的值小于等于index2的值，那么中点肯定是在右侧的升序数组中，那么目标点肯定在左边，于是令index2=midIndex，
 * 结束条件就是index1和index2相邻，即index1 + 1 == index2，最终结果就是index2的值（也就是右侧升序数组的第一个元素）
 *
 * 但是上面还有缺陷，不能解决index1，index2，midIndex三个值相同的情况，
 * 比如{1,0,1,1,1}，这按照上面你的算法得到的结果是1...
 * 所以当三个值相等时，就在[index1,index2]范围内进行顺序查找
 */
public class 旋转数组的最小数字_11 {
    public static void main(String[] args) {
        旋转数组的最小数字_11 a = new 旋转数组的最小数字_11();
        System.out.println(a.minArray(new int[]{3,4,5,1,2}));
        System.out.println(a.minArray(new int[]{1,2,3,4,5}));
        System.out.println(a.minArray(new int[]{1}));
        System.out.println(a.minArray(new int[]{}));
        System.out.println(a.minArray(new int[]{1,0,1,1,1}));
    }

    public int minArray(int[] numbers) {
        if(numbers == null || numbers.length == 0) {
            return -1;
        }
        int index1 = 0;
        int index2 = numbers.length - 1;
        int midIndex = 0;
        // 之所以有numbers[index1] >= numbers[index2]这个判断，是为了防止原数组整个是升序的情况
        while (numbers[index1] >= numbers[index2]) {
            if (index2 - index1 == 1) {
                midIndex = index2;
                break;
            }

            midIndex = (index2 - index1) / 2 + index1;
            if (numbers[midIndex] == numbers[index1] && numbers[midIndex] == numbers[index2]) {
                return sequentialSearch(numbers, index1, index2);
            }

            if (numbers[midIndex] >= numbers[index1]) {
                index1 = midIndex;
            }else if (numbers[midIndex] <= numbers[index2]) {
                index2 = midIndex;
            }
        }
        return numbers[midIndex];
    }

    private int sequentialSearch(int[] numbers, int index1, int index2) {
        int min = Integer.MAX_VALUE;
        for (int i = index1; i <= index2; i++) {
            min = Math.min(min, numbers[i]);
        }
        return min;
    }
}
