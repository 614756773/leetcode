package com.hard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Date: 2019/8/21
 * @Author: qinzhu
 */
public class H2O生成 {
    public static void main(String[] args) throws InterruptedException {
        H2O h2O = new H2O();
        ExecutorService pool = Executors.newFixedThreadPool(2);
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
            for (int i = 0; i < n * 2; i++) {
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

    private Semaphore o = new Semaphore(1);
    private Semaphore h = new Semaphore(2);
    private int existH = 0;

    H2O() {}

    void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();
        existH++;
        if (existH == 2) {
            o.release();
            existH = 0;
        }
    }

    void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        releaseOxygen.run();
        h.release(2);
    }
}
