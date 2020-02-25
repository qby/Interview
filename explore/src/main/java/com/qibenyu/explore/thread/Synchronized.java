package com.qibenyu.explore.thread;

/**
 *
 */
public class Synchronized {


    /**
     * 修饰静态方法，
     */
    public synchronized static void staticMethod() {

    }

    /**
     * 修饰对象方法
     */
    public synchronized void objectMethod() {

    }

    public void range() {

        /**
         * 代码块
         */
        synchronized (Synchronized.class) {

        }
    }
}
