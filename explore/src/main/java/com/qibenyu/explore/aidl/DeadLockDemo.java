package com.qibenyu.explore.aidl;

import java.util.concurrent.TimeUnit;


public class DeadLockDemo {

    static class Data implements Runnable {

        public Data(String lock1, String lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        final String lock1;
        final String lock2;

        @Override
        public void run() {

            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "持有 " + lock1 + "， 尝试获得" + lock2);

                try {
                    TimeUnit.SECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "持有" + lock2 + ", 尝试获得" + lock1);
                }

            }

        }
    }

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new Data(lockA, lockB)).start();
        new Thread(new Data(lockB, lockA)).start();
    }
}
