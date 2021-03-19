package com.medium.链表;

/**
 * @author: hotpot
 * @since: 2021/03/18
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 寻找重复数 {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        // 快慢指针，先找到重合点
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);

        // 步长都变为1
        fast = nums[0];
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }

    public int findDuplicate_(int[] nums) {
        boolean[] hash = new boolean[nums.length];
        for (int num : nums) {
            if (hash[num]) {
                return num;
            }
            hash[num] = true;
        }
        return -1;
    }
}
