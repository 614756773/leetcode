package other;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @Date: 2019/6/12
 * @Author: qinzhu
 */
public class 布隆过滤器排序 {
    public static void main(String[] args) throws Exception {
        BitArray bitArray = new BitArray(100000000);
        bitArray.setBit(21875524, 1);
        bitArray.setBit(28133879, 1);
        for (int i = 0; i < 32; i++) {
//            if(bitArray.getBit(i) == 1) {
//                System.out.println(i);
//            }
            System.out.println(bitArray.getBit(i));
        }
//        String phoneNum;
//        int phoneNumAsInt;
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(""));
//        //顺序读取所有的手机号码
//        while ((phoneNum = bufferedReader.readLine()) != null) {
//            //取139号码的最后8位数字
//            phoneNum = phoneNum.trim().substring(3);
//            //将后8位转换为int类型
//            phoneNumAsInt = Integer.valueOf(phoneNum);
//            //设置对应bit值为1
//            bitArray.setBit(phoneNumAsInt, 1);
//        }
//        //遍历bit数组输出所有存在的号码
//        for (int i = 0; i < 32; i++) {
//            if (bitArray.getBit(i) == 1) {
//                System.out.println(i);
////                writer.write("139" + leftPad(String.valueOf(i + sortUnit * times), 8));
////                writer.newLine();
//            }
//        }
    }
}

class BitArray {
    private int[] bits = null;
    private int length;
    //用于设置或者提取int类型的数据的某一位(bit)的值时使用
    private final static int[] bitValue = {
            0x80000000,//10000000 00000000 00000000 00000000
            0x40000000,//01000000 00000000 00000000 00000000
            0x20000000,//00100000 00000000 00000000 00000000
            0x10000000,//00010000 00000000 00000000 00000000
            0x08000000,//00001000 00000000 00000000 00000000
            0x04000000,//00000100 00000000 00000000 00000000
            0x02000000,//00000010 00000000 00000000 00000000
            0x01000000,//00000001 00000000 00000000 00000000
            0x00800000,//00000000 10000000 00000000 00000000
            0x00400000,//00000000 01000000 00000000 00000000
            0x00200000,//00000000 00100000 00000000 00000000
            0x00100000,//00000000 00010000 00000000 00000000
            0x00080000,//00000000 00001000 00000000 00000000
            0x00040000,//00000000 00000100 00000000 00000000
            0x00020000,//00000000 00000010 00000000 00000000
            0x00010000,//00000000 00000001 00000000 00000000
            0x00008000,//00000000 00000000 10000000 00000000
            0x00004000,//00000000 00000000 01000000 00000000
            0x00002000,//00000000 00000000 00100000 00000000
            0x00001000,//00000000 00000000 00010000 00000000
            0x00000800,//00000000 00000000 00001000 00000000
            0x00000400,//00000000 00000000 00000100 00000000
            0x00000200,//00000000 00000000 00000010 00000000
            0x00000100,//00000000 00000000 00000001 00000000
            0x00000080,//00000000 00000000 00000000 10000000
            0x00000040,//00000000 00000000 00000000 01000000
            0x00000020,//00000000 00000000 00000000 00100000
            0x00000010,//00000000 00000000 00000000 00010000
            0x00000008,//00000000 00000000 00000000 00001000
            0x00000004,//00000000 00000000 00000000 00000100
            0x00000002,//00000000 00000000 00000000 00000010
            0x00000001 //00000000 00000000 00000000	00000001
    };

    public BitArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length必须大于零！");
        }
        bits = new int[length / 32 + (length % 32 > 0 ? 1 : 0)];
        this.length = length;
    }

    //取index位的值
    int getBit(int index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length必须大于零小于" + length);
        }
        int intData = bits[index / 32];
        int result = (intData & bitValue[index % 32]) >>> (32 - index % 32 - 1);
        return result;
    }

    //设置index位的值，只能为0或者1
    void setBit(int index, int value) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length必须大于零小于" + length);
        }
        if (value != 1 && value != 0) {
            throw new IllegalArgumentException("value必须为0或者1");
        }
        int intData = bits[index / 32];
        if (value == 1) {
            bits[index / 32] = intData | bitValue[index % 32];
        } else {
            bits[index / 32] = intData & ~bitValue[index % 32];
        }
    }

    public int getLength() {
        return length;
    }
}

