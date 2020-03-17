package com.qibenyu.explore.basis.thread;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程和进程： 是否是共享数据
 *
 * CPU线程和操作系统线程
 *
 */
public class ThreadTest {

    private static final String TAG = "ThreadTest";

    /**
     * 线程工厂，统一初始化操作。
     */
    public void threadFactory() {
        ThreadFactory factory = new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread("name " + count.getAndIncrement());
            }
        };

        factory.newThread(() -> {

        }).start();

    }

    /**
     * 指定线程的一个工具接口
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
     * 参数：
     *
     * public ThreadPoolExecutor(int corePoolSize,//核心线程数量， 初始线程数量和不释放的线程数量
     * int maximumPoolSize, // 最大线程数量，其余排队
     * long keepAliveTime, // 核心线程存活时间
     * TimeUnit unit, //存活时间单位
     * BlockingQueue<Runnable> workQueue) { // 待执行队列
     */
    public void exector() {

        /**
         * 实现Executors 接口
         * 增加 shutdown方法(); 保守型方法，如果正在执行或者已经排队了，会执行完。
         * shutdownNow(); 立即结束当前线程和排队
         */
        ExecutorService service = Executors.newCachedThreadPool();
        /**
         * 单线程
         */
        Executors.newSingleThreadExecutor();

        /**
         * 固定线程池, 一次性的批量操作
         */
        Executors.newFixedThreadPool(10);

        /**
         * 延时多久后执行 ,延迟2s 后执行
         *
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2000);
        scheduledExecutorService.schedule(() -> {

        }, 2000, TimeUnit.MILLISECONDS);

        service.execute(() -> {

            Log.d(TAG, "exector: ");
        });

    }

    /**
     * 有返回值的Runnable
     */
    public void callable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1500);
                return "aaaa";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable);

        try {
            /**
             * 瞬时返回
             */
            boolean isDone = future.isDone();

            /**
             * get为阻塞方法
             */
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
