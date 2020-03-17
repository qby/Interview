package com.qibenyu.explore.basis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueue {

    /**
     * ArrayBlockingQueue  底层是数组
     * LinkedBlockingQueue  有界阻塞队列，但是默认值是Integer.MAX_VALUE相当与无界
     * SynchronousQueue 不存储元素的阻塞队列
     * <p>
     * <p>
     * Deque 由链表组成的双向队列
     * <p>
     * <p>
     *              插入      移除                      检查(拿到队列头元素)
     * 抛出异常     add()    remove()                   element() 超出queue的容量 会抛出异常
     * 返回布尔型   offer()  poll()(没有元素返回null)      peek()
     * 阻塞        put()    take()
     */

    public void queueTest() {

        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.offer(1);
        queue.offer(2);
        try {
            //超过容量后堵着，直到能放进去
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            //超过容量后堵着，等2秒后如果插不进去就放弃
            queue.offer(3, 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queue.element();
        queue.peek();

        queue.poll();
        queue.remove();
        queue.remove();

        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生产一个，消费一个，如果没有消费，put时会被阻塞
     */
    public void syncQueue() {

        BlockingQueue<String> queue = new SynchronousQueue<>();
    }

}
