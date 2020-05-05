package com.medium;

/**
 * @author qinzhu
 * @since 2020/4/27
 * 思路：
 *  首先肯定是需要用二分搜索的，但是因为数组被旋转过，所以需要多一步操作，如下：
 *      在使用二分搜索之前，先判断当前子数组的头是否大于尾，如果没有那就说明是有序的，直接使用二分搜索，
 *      如果是头大于尾说明这个子数组包含旋转后的，于是把这个子数组从中间切成2块，
 *      递归处理切出来的2个子数组
 * 细节：
 *  使用二分的思想时一定要保证以下两点：
 *      ① left < right或者left <= right
 *      ② 在fun(left, right)函数中，头和尾都会被处理到，
 *          所以下一层就该这样写：execute(mid);fun(left, mid - 1);fun(mid + 1, right);
 */
public class 搜索旋转排序数组 {
    private int ans = -1;
    private int target;
    private int[] nums;

    public static void main(String[] args) {
        System.out.println(new 搜索旋转排序数组().search(new int[]{2,1}, 0));
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return ans;
        }
        this.target = target;
        this.nums = nums;
        recursive(0, nums.length - 1);
        return ans;
    }

    private void recursive(int left, int right) {
        // 已经出结果了，就不要再查找
        if (ans != -1 || left > right) {
            return;
        }

        if (nums[left] > nums[right]) {
            int mid = (right - left + 1) / 2 + left;
            if (nums[mid] == target) {
                ans = mid;
                return;
            }
            recursive(left, mid - 1);
            recursive(mid + 1, right);
        } else {
            binarySearch(left, right);
        }
    }

    private void binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (right - left + 1) / 2 + left;
            if (mid >= nums.length) {
                return;
            }
            int num = nums[mid];
            if (num == target) {
                ans = mid;
                break;
            } else if (num < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}
