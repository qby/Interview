package com.qibenyu.explore;

import android.util.Log;

public class MemoryModel {

    public static class  MyData {
        volatile int number = 0;

        public void setNumber() {
            number = 10;
        }
    }

    public static void main(String[] args) {

        MyData data = new MyData();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.setNumber();
                System.out.println("out : " + data.number);

            }
        }.start();

        while (data.number == 0) {

        }

        System.out.println(Thread.currentThread().getName() + ", over");
    }



}
