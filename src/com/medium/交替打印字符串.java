package com.medium;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author qinzhu
 * @since 2020/7/13
 */
public class 交替打印字符串 {
    public static void main(String[] args) throws InterruptedException {
        FizzRunnable f = new FizzRunnable();
        BuzzRunnable b = new BuzzRunnable();
        FizzbuzzRunnable fb = new FizzbuzzRunnable();
        FizzBuzz target = new FizzBuzz(90);

        start4thread(f, b, fb, target);
    }

    /**
     * 启动四个线程
     */
    private static void start4thread(FizzRunnable f, BuzzRunnable b, FizzbuzzRunnable fb, FizzBuzz target) {
        new Thread(() -> {
            try {
                target.fizz(f);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                target.buzz(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                target.fizzbuzz(fb);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                target.number(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class FizzBuzz {
        private int n;
        private Semaphore _3;
        private Semaphore _5;
        private Semaphore _15;
        private Semaphore normal;

        public FizzBuzz(int n) {
            this.n = n;
            this._3 = new Semaphore(0);
            this._5 = new Semaphore(0);
            this._15 = new Semaphore(0);
            this.normal = new Semaphore(1);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            _3.acquire();
            for (int i = 3; i <= n; i += 3) {
                if (i % 5 == 0) {
                    // 防止最后被唤醒时处理到5的倍数
                    continue;
                }
                printFizz.run();
                normal.release();
                tryReleaseAll(i);
                _3.acquire();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            _5.acquire();
            for (int i = 5; i <= n; i += 5) {
                if (i % 3 == 0) {
                    // 防止最后被唤醒时处理到3的倍数
                    continue;
                }
                printBuzz.run();
                normal.release();
                tryReleaseAll(i);
                _5.acquire();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            _15.acquire();
            for (int i = 15; i <= n; i += 15) {
                printFizzBuzz.run();
                normal.release();
                tryReleaseAll(i);
                _15.acquire();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            normal.acquire();
            for (int i = 1; i <= n; i++) {
                if (i % 15 == 0) {
                    _15.release();
                    normal.acquire();
                    continue;
                }
                if (i % 3 == 0) {
                    _3.release();
                    normal.acquire();
                    continue;
                }
                if (i % 5 == 0) {
                    _5.release();
                    normal.acquire();
                    continue;
                }
                printNumber.accept(i);
                tryReleaseAll(i);
            }
        }

        /**
         * 当执行玩最后一个数字时，将阻塞的线程唤醒，以便它们结束运行
         */
        private void tryReleaseAll(int i) {
            if (i == n) {
                _3.release();
                _5.release();
                _15.release();
                normal.release();
            }
        }

    }

    static class FizzRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("fizz");
        }
    }

    static class BuzzRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("buzz");
        }
    }

    static class FizzbuzzRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("fizzbuzz");
        }
    }
}
