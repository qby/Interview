package com.qibenyu.explore.basis;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SafeCollection {

    /**
     * ArrayList 线程不安全   CopyOnWriteArrayList
     */
    public void copyOnWrite() {



        /**
         * 写入数据时new新数组len+1 ，进行数组数据copy，然后指向新数组
        public boolean add(E e) {
            synchronized (lock) {
                Object[] elements = getArray();
                int len = elements.length;
                Object[] newElements = Arrays.copyOf(elements, len + 1);
                newElements[len] = e;
                setArray(newElements);
                return true;
            }
        }
         */
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();

        /**
         * 底层原理为CopyOnWriteArrayList
         */
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

    }

    /**
     * 分段锁
     */
    public void concurrent() {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();


    }


}
