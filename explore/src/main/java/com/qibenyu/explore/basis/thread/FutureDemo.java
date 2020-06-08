package com.qibenyu.explore.basis.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future future = service.submit(() -> {

            System.out.println("执行耗时操作...");
            timeConsumingOperation();
            return 100;
        });

        System.out.println("计算结果:" + future.get());//<2>
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l)+ "ms");
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
