package com.medium;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Date: 2019/8/22
 * @Author: qinzhu
 */
public class 打印零与奇偶数 {
    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd a = new ZeroEvenOdd(2);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(() -> {
                try {
                    a.zero(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        pool.execute(() -> {
                try {
                    a.even(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        pool.execute(() -> {
                try {
                    a.odd(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        pool.shutdown();
    }
}
class ZeroEvenOdd {
    private volatile int n;
    private volatile int oNum = 1;
    private volatile int eNum = 2;
    private Semaphore z = new Semaphore(1);
    private Semaphore o = new Semaphore(1);
    private Semaphore e = new Semaphore(1);

    public ZeroEvenOdd(int n) {
        this.n = n;
        try {
            e.acquire();
            o.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            z.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                o.release();
            } else {
                e.release();
            }
        }
    }

    // 偶数
    public void even(IntConsumer printNumber) throws InterruptedException {
        while (eNum <= n) {
            e.acquire();
            printNumber.accept(eNum);
            eNum += 2;
            z.release();
        }
    }

    // 奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (oNum <= n) {
            o.acquire();
            printNumber.accept(oNum);
            oNum += 2;
            z.release();
        }
    }
}