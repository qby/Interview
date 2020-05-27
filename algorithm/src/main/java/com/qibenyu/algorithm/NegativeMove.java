package com.qibenyu.algorithm;

import com.qibenyu.base.extension.ExtenisonsKt;

import java.util.Arrays;
import java.util.Stack;

public class NegativeMove {

    public static void main(String[] args) {

        int[] array = new int[]{10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35};
        move(array);

        System.out.println(Arrays.toString(array));
    }

    public static void move(int[] array) {

        int index = 0;

        for (int i = 0; i < array.length; i++) {

            if (array[i] < 0) {
                ExtenisonsKt.exch(array, index, i);
                index++;
            }

        }

    }
}
