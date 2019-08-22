package com.medium;

import java.util.concurrent.Semaphore;

/**
 * @Date: 2019/8/22
 * @Author: qinzhu
 */
public class 交替打印FooBar {
    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
class FooBar {
    private int n;
    private Semaphore f = new Semaphore(1);
    private Semaphore b = new Semaphore(1);

    public FooBar(int n) {
        this.n = n;
        try {
            b.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            f.acquire();
            printFoo.run();
            b.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            b.acquire();
            printBar.run();
            f.release();
        }
    }
}
