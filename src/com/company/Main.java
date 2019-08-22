package com.company;

import java.util.UUID;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        A a = new A();
        executors.execute(a);
        executors.execute(a);
        executors.execute(a);
        executors.shutdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService executors2 = Executors.newFixedThreadPool(3);
        executors2.execute(a);
        executors2.execute(a);
        executors2.execute(a);
        executors2.shutdown();
    }

    static class A implements Runnable{
        private CyclicBarrier barrier = new CyclicBarrier(3);
        private CountDownLatch latch = new CountDownLatch(3);

        @Override
        public void run() {
            UUID uuid = UUID.randomUUID();
            System.out.println("loading.." + uuid.toString());
            latch.countDown();
            try {
//                barrier.await();
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ok.." + uuid.toString());
        }
    }
}
