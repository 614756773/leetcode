package com.simple;


import java.util.concurrent.CountDownLatch;

/**
 * @Date: 2019/8/22
 * @Author: qinzhu
 */
public class 按序打印 {
    public static void main(String[] args) {

    }
}

class Foo {
    private CountDownLatch second = new CountDownLatch(1);
    private CountDownLatch third = new CountDownLatch(1);

    public Foo() {}

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        second.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.await();
        printSecond.run();
        third.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.await();
        printThird.run();
    }
}
