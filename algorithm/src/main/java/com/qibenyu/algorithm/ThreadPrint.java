package com.qibenyu.algorithm;

/**
 * 三个线程打印
 * 1A， 2B， 3C， 1D， 2E， 3F
 */
public class ThreadPrint {

    char[] array = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};

    private static Object lock = new Object();

    public static void main(String[] args) {

        new ThreadPrint().p();

    }

    int index = 0;

    private void p() {

        new Thread(() -> {

            synchronized (this) {

                while (true) {
                    whileTrue(1);
                }

            }
        }, "1").start();

        new Thread(() -> {

            while(true){
                whileTrue(2);
            }
        }, "2").start();

        new Thread(() -> {

            while (true) {
                whileTrue(0);
            }
        }, "3").start();
    }

    private synchronized void whileTrue(int i) {
        while (((index + 1) % 3) != i) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        print(Thread.currentThread().getName(), index);
        index++;
        notifyAll();


    }

    private void print(String num, int index) {
        if (index>= array.length)  return;
        System.out.println(num + array[index]);
    }
}
