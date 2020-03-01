package com.qibenyu.algorithm;

import android.util.Log;

import com.qibenyu.base.extension.ExtenisonsKt;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

public class PriorityQueue implements IAlgorithm {
    private static final String TAG = "PriorityQueue";

    private int[] array;

    private int index = 0;

    private int size;

    public PriorityQueue() {
        this(10);
    }

    public PriorityQueue(int size) {

        this.size = size;

        array = new int[size + 1];

    }

    public void put(int e) {

        index++;

        array[index] = e;

        swim(index);


    }

    private void swim(int k) {

        while (k > 1 && array[k] > array[k / 2]) {
            ExtenisonsKt.exch(array, k / 2, k);
            k = k / 2;
        }
    }

    public int getMax() {

        int e = array[1];

        array[1] = array[index];

        index--;

        sink(1);

        return e;
    }

    private void sink(int k) {

        while (2 * k <= index) {

            int j = 2 * k;
            if (j < index && array[j] < array[j + 1]) j++;

            if (array[k] > array[j]) break;

            ExtenisonsKt.exch(array, k, j);

            k = j;
        }
    }

    @NotNull
    @Override
    public String problem() {
        return "手写优先级队列";
    }

    @NotNull
    @Override
    public String condition() {
        return "";
    }

    @NotNull
    @Override
    public String thought() {
        return "";
    }

    @NotNull
    @Override
    public String answer() {

        PriorityQueue queue = new PriorityQueue(20);

        for (int i = 0; i < 10; i++) {
            queue.put(i);
        }

        Log.d(TAG, "answer: list = " + Arrays.toString(array));


        for (int i = 0; i < 10; i++) {

            int max = queue.getMax();
            Log.d(TAG, "answer: " + max);
        }


        return "";
    }
}
