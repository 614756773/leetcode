package other.限流算法;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author: hotpot
 * @since: 2021/03/07
 * https://www.cnblogs.com/dijia478/p/13807826.html
 */
public class 滑动窗口限流 {
    /**
     * 窗口
     */
    private LinkedList<Long> window;

    /**
     * 窗口大小
     */
    private int windowSize;

    /**
     * 访问间隔，单位毫秒
     */
    private int interval;

    /**
     * 表示在每{@code interval}毫秒内，只允许最多@{code windowSize}次访问
     */
    public 滑动窗口限流(int interval, int windowSize) {
        this.interval = interval;
        this.window = new LinkedList<>();
        this.windowSize = windowSize;
    }

    /**
     * 访问，如果返回false表示被限流了，如果返回true表示能正常访问
     */
    public synchronized boolean visit() {
        long currentTime = System.currentTimeMillis();
        if (window.size() < windowSize) {
            window.addFirst(currentTime);
            return true;
        }

        Long earliestTime = window.getLast();
        if (currentTime - earliestTime < interval) {
            return false;
        }

        window.removeLast();
        window.addFirst(currentTime);
        return true;
    }

    public static void main(String[] args) {
        滑动窗口限流 limitUtil = new 滑动窗口限流(5000, 3);
        System.err.println(String.format("滑动窗口限流测试，每%d秒内只允许通过%d次请求", 5, 3));
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 8; j++) {
                    try {
                        Thread.sleep(new Random().nextInt(5) * 1000);
                        boolean visitFlag = limitUtil.visit();
                        System.out.println(Thread.currentThread() + "，时间「" + LocalDateTime.now().getSecond() + "」，访问「" + visitFlag + "」");
                    } catch (InterruptedException ignored) {
                    }
                }
            }).start();
        }
    }
}
