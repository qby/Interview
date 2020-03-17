package com.qibenyu.explore.basis.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock {

    /**
     * 读写锁， 读为共享， 写为独占锁
     */
    public void reentrantReadWriteLock() {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        lock.readLock().unlock();

        lock.writeLock().lock();
        lock.writeLock().unlock();
    }

    /**
     * synchronized 和lock 的区别
     */
    private void diff() {
        /**
         * 1. synchronized 属于关键字，底层使用monitor完成
         *      Lock是具体类
         *
         * 2. synchronized 不需要手动释放
         *      Lock需要手动释放，，若没有手动释放会出现死锁try finally 语句
         *
         * 3。 synchronized 不可中断
         *      Lock可以中断  tryLock(time,unit)  lock
         *
         * 4. 加锁是否公平
         *      synchronized 非公平锁
         *      ReentrantLock 默认非公平锁，可以公平
         *
         * 5. 锁绑定多个Condition
         *
         */

    }
}
