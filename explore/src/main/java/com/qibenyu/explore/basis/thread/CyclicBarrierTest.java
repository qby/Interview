package com.qibenyu.explore.basis.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest extends Thread {



    CyclicBarrier barrier;
    public CyclicBarrierTest(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {

        try {
            System.out.println("Hello " + Thread.currentThread().getName());
            barrier.await();
            System.out.println("World!" + Thread.currentThread().getName());
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(5);

        ExecutorService executors = Executors.newFixedThreadPool(5);
        executors.execute(new CyclicBarrierTest(cb));
        executors.execute(new CyclicBarrierTest(cb));
        executors.execute(new CyclicBarrierTest(cb));
        executors.execute(new CyclicBarrierTest(cb));
        executors.execute(new CyclicBarrierTest(cb));


    }
}
