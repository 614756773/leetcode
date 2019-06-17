package other;

/**
 * @Date: 2019/6/14
 * @Author: qinzhu
 */
public class 加强版的hash排序 {
    static int[] masks = new int[]{
            0b10000000000000000000000000000000, // 1
            0b01000000000000000000000000000000, // 2
            0b00100000000000000000000000000000, // 3
            0b00010000000000000000000000000000, // 4
            0b00001000000000000000000000000000, // 5
            0b00000100000000000000000000000000, // 6
            0b00000010000000000000000000000000, // 7
            0b00000001000000000000000000000000, // 8
            0b00000000100000000000000000000000, // 9
            0b00000000010000000000000000000000, // 10
            0b00000000001000000000000000000000, // 11
            0b00000000000100000000000000000000, // 12
            0b00000000000010000000000000000000, // 13
            0b00000000000001000000000000000000, // 14
            0b00000000000000100000000000000000, // 15
            0b00000000000000010000000000000000, // 16
            0b00000000000000001000000000000000, // 17
            0b00000000000000000100000000000000, // 18
            0b00000000000000000010000000000000, // 19
            0b00000000000000000001000000000000, // 20
            0b00000000000000000000100000000000, // 21
            0b00000000000000000000010000000000, // 22
            0b00000000000000000000001000000000, // 23
            0b00000000000000000000000100000000, // 24
            0b00000000000000000000000010000000, // 25
            0b00000000000000000000000001000000, // 26
            0b00000000000000000000000000100000, // 27
            0b00000000000000000000000000010000, // 28
            0b00000000000000000000000000001000, // 29
            0b00000000000000000000000000000100, // 30
            0b00000000000000000000000000000010, // 31
            0b00000000000000000000000000000001, // 32
    };

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 132, 5, 7, 456, 9, 10, 12, 13, 78, 16, 465, 19, 20, 213, 23, 89, 26, 94, 32, 97, 47, 48, 56, 123};
//        sort_(nums);
        sort(nums);
    }

    /**
     *
     * @param nums 要求数字没有重复，排序0~1亿的数需要11MB左右的内存
     */
    private static void sort(int[] nums) {
        // 1.确定hash桶的数量，一个hash桶最多可以存放32个数
        int max = max(nums);
        int n = (max >> 5) + (max & (31));// 等价于(max / 32) + max % 32;
        int buckets[] = new int[n];
        for (int num : nums) {
            // 2.确定桶索引和桶内索引
            int bucketsIndex = (num >> 5) - 1;
            int bucketIndex;
            int tmp = num & 31;
            if ((tmp) != 0) {
                bucketsIndex++;
                bucketIndex = tmp - 1;
            } else {
                if (num == 0) {
                    throw new RuntimeException("暂时不支持含0的排序，因为1~32存放在第一个int中，33~64存放在第二个int中...");
                } else {
                    bucketIndex = 31;
                }
            }
            // 3.用为操作将该数记录
            buckets[bucketsIndex] = buckets[bucketsIndex] | masks[bucketIndex];
        }
        // 4.输出
        for (int i = 0; i < buckets.length; i++) {
            int bucket = buckets[i];
            for (int j = 0; j < 32; j++) {
                int tmp = bucket & masks[j];
                tmp = tmp >> (32 - 1 - j);
                if (tmp == 1 || tmp == -1) {
                    int result = i * 32 + (j + 1);
                    System.out.print(result + ",");
                }
            }
        }
    }

    /**
     * @param nums 要求最大数小于等于32，并且没有重复
     */
    private static void sort_(int[] nums) {
        if (max(nums) > 32) {
            throw new RuntimeException("都说了这个低配DEMO不支持含32以上的数");
        }
        
        // 用1个int作hash桶，可以存放32个数字
        int bucket = 0b00000000000000000000000000000000;
        for (int num : nums) {
            bucket = bucket | masks[num - 1];
        }
        for (int i = 0; i < 32; i++) {
            int bitValue = bucket & masks[i];
            bitValue = bitValue >> (32 - i - 1);
            // 等于1时表明这个位之前被标记过，不过有一个特殊的情况：
            // 32位中最左边那一位是符号位，用位运算取出来的该位值虽然是1但是结果会被变成-1
            if (bitValue == 1 || bitValue == -1) {
                System.out.println(i + 1);
            }
        }
    }

    private static int max(int[] nums) {
        if (nums == null) {
            throw new RuntimeException();
        }

        int max = nums[0];
        for (int num : nums) {
            max = max > num ? max : num;
        }
        return max;
    }
}
