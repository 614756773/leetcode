package com.company;

import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        A a = new A();
        executors.execute(a);
        executors.execute(a);
        executors.execute(a);
        executors.shutdown();
    }

    static class A implements Runnable{
        private CyclicBarrier barrier = new CyclicBarrier(3);

        @Override
        public void run() {
            UUID uuid = UUID.randomUUID();
            System.out.println("loading.." + uuid.toString());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("ok.." + uuid.toString());
        }
    }
}
