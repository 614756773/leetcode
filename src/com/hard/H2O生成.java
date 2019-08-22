package com.hard;

import java.util.concurrent.*;

/**
 * @Date: 2019/8/21
 * @Author: qinzhu
 */
public class H2O生成 {
    public static void main(String[] args) throws InterruptedException {
        H2O h2O = new H2O();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int n = 5;
        pool.execute(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    h2O.oxygen(() -> System.out.println("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.execute(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    h2O.hydrogen(() -> System.out.println("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.execute(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    h2O.hydrogen(() -> System.out.println("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.shutdown();
    }
}

class H2O {

    // 可生产的O元素和H元素数量
    private Semaphore o = new Semaphore(1);
    private Semaphore h = new Semaphore(2);
    private CyclicBarrier barrier = new CyclicBarrier(3);

    H2O() {}

    void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        releaseHydrogen.run();
        h.release();
    }

    void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        releaseOxygen.run();
        o.release();
    }
}
