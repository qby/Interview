package com.qibenyu.explore.basis.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLock {

    private AtomicReference<Thread> thread = new AtomicReference<>();

    public void lock() {

        Thread t = Thread.currentThread();

        while (!thread.compareAndSet(null, t)) {

        }

    }

    public void unlock() {
        Thread t  = Thread.currentThread();
        thread.compareAndSet(t, null);
    }

    public static void main(String[] args) {

        SpinLock lock = new SpinLock();

        new Thread() {
            @Override
            public void run() {

                try {
                    lock.lock();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }


            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread() {
            @Override
            public void run() {
                lock.lock();
                lock.unlock();
            }
        }.start();


    }
}
