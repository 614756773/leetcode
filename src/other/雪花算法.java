package other;

import java.net.UnknownHostException;
import java.time.LocalDate;

/**
 * @Date: 2019/9/9
 * @Author: qinzhu
 * <p>雪花算法生成分布式全局ID</p>
 * <p>
 *     一共使用64位,第1位不使用,41位时间戳,10位工作机器ID, 12位序列号,
 *      时间戳取的是【当前时间 - 某个设定的开始时间】,这样做能保证时间戳是11位的数字,
 *      这11位的时间戳数字使用的8字节（即）64位的long类型存储,
 *      然后左移22位（机器位数 + 序列位数）,恰好把高位的22个0都移除了。
 * </p>
 * 最后在按位加上机器号（需要左移12位,即序列号的位数）,按位加上序列号就完成了
 */
public class 雪花算法 {
    private static long startTime = LocalDate.of(1996,11,27).toEpochDay();
    private static int machineBit = 10;
    private static int sequenceBit = 12;

    private static long lastSequence = -1L;
    private static long lastTimestamp = -1L;
    private static int maxSequence = (1 >> sequenceBit) - 1;

    public static void main(String[] args) throws UnknownHostException {
        int machineId = 12;
        for (int i = 0; i < 20; i++) {
            System.out.println(buildGlobalId(machineId));
        }
    }

    private synchronized static long buildGlobalId(int machineId) {
        long currentTime = getNextTimestamp();
        // 相同毫秒内序列号递增,新的毫秒则序列号从零开始
        if (currentTime == lastSequence) {
            long sequence = (lastSequence + 1) & maxSequence;
            // 当前毫秒的序列号已经全部用完则重新获取一次
            if (sequence == 0) {
                return buildGlobalId(machineId);
            }
            lastSequence = sequence;
        }else {
            lastSequence = 0;
        }

        lastTimestamp = currentTime;
        return (lastTimestamp - startTime) << (sequenceBit + machineBit) |
                machineId << sequenceBit |
                lastSequence;
    }

    private static long getNextTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        if (lastTimestamp < 0) {
            lastTimestamp = currentTimeMillis;
            return currentTimeMillis;
        }

        while (currentTimeMillis <= lastTimestamp) {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis;
    }
}
