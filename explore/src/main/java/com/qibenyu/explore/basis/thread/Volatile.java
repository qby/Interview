package com.qibenyu.explore.basis.thread;

/**
 *
 *
 *
 * volatile 类型修饰符 ,确保本条指令不会因为编译器的优化而省略。
 *
 * 1. 保存不同线程对这个变量操作的可见性，即一个线程修改了某个变量的值，这个新的值对其他的线程是看见的
 * 2. 禁止指令重排序
 * 3. volatile 不保证了读/写的原子性。
 *
 */
public class Volatile {
}
