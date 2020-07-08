package com.qibenyu.algorithm;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadAlgorithm {

    private static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while(integer.get() < 1000) {
                    integer.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " = " + integer.get());
                }
            }).start();
        }
        int[] a = new int[]{1};
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(integer.get());
    }
}
