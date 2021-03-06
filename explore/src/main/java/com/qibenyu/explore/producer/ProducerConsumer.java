package com.qibenyu.explore.producer;

import android.util.Log;

public class ProducerConsumer {

    private static final String TAG = "ProducerConsumer";

    static class Producer implements Runnable {

        private Worker mWorker;
        public Producer(Worker worker) {
            mWorker = worker;
        }
        @Override
        public void run() {

            while (true) {
                try {
                    mWorker.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer implements Runnable {

        private Worker mWorker;

        public Consumer(Worker worker) {
            mWorker = worker;
        }

        @Override
        public void run() {

            while (true) {
                try {
                    mWorker.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Worker {

        private final Object object = new Object();
        private int product = 0;

        public void produce() throws InterruptedException {

            synchronized (object) {
                // 使用while 不用if 判断 是因为：
                // 如果多个线程同时wait在这里
                // 在消费者notifyAll后，不会再判断标记, 就会往下执行
                // 会造成 生产多余指定目标，或者 消费低于最小值的情况
                while (product >= 5) {
                    System.out.println("produce: 等待消费 ------" + product);
                    object.wait();
                }

                product++;
                System.out.println("produce: 生产 ------" + product);
                object.notifyAll();

            }
        }

        public void consume() throws InterruptedException {
            synchronized (object) {

                while (product == 0) {
                    System.out.println("produce: 等待生产 ------" + product);
                    object.wait();
                }

                product--;
                System.out.println( "consume: 消费 -----" + product);

                object.notifyAll();
            }
        }
    }


    public static void main(String[] args) {

        Worker worker = new Worker();
        new Thread(new Producer(worker)).start();
        new Thread(new Consumer(worker)).start();
        new Thread(new Producer(worker)).start();
        new Thread(new Consumer(worker)).start();
        new Thread(new Producer(worker)).start();
        new Thread(new Consumer(worker)).start();
        new Thread(new Producer(worker)).start();
        new Thread(new Consumer(worker)).start();

    }
}
