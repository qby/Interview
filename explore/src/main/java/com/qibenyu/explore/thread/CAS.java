package com.qibenyu.explore.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * (原子)
 * AtomicInteger
 */
public class CAS {

    /**
     *
     * CAS(Compare And Swap) 内存位置的值V,旧的预期值A，要修改的新值B。 如果内存值和预期值A比较相等，将内存值V修改为B , 否则什么都不做
     *
     * CAS 有效地说明了“我认为位置 V 应该包含值 A；如果包含该值，则将 B 放到这个位置；否则，不要更改该位置，只告诉我这个位置现在的值即可。”*
     *
     * 缺点：
     * 1. 只能保持一个变量的原子性操作。 多个变量时可以用锁
     * 2. 长时间自旋会给CPU带来压力。 getAndIncrement() 方法在执行的时候，如果CAS失败，会一直进行尝试。如果CAS一直不成功，会一直尝试
     * 3. ABA问题：如果内存地址V初次读取的值是A, 并且在赋值的时候检查他的值仍为A，但是此时不能完全证明V的值没有被修改过。
     *      后来Java对其变量前增加版本号，判断变量是否被其他线程修改过。
     */
    private AtomicInteger count = new AtomicInteger(0);

    public AtomicInteger test() {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {

            executor.execute(() -> {

                for (int j = 0; j < 10000; j++) {
                    /**
                     * synchronized 属于悲观锁，它的一个明显的缺点是，不管数据是否存在竞争都加锁，，随着并发数量增多开销越来越大。
                     * 解决办法是乐观锁
                     */
//                    synchronized (CAS.class) {
//                        count++;
                        count.getAndIncrement();
//                    }
                }


            });
        }

        executor.shutdown();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);

        return count;


    }
}
