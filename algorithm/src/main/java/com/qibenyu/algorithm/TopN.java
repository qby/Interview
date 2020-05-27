package com.qibenyu.algorithm;

import com.qibenyu.base.extension.ExtenisonsKt;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopN {

    /**
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getTopN1(int[] arr, int k) {

        Queue<Integer> queue = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));


        for (int a : arr) {
            if (queue.isEmpty() || queue.size() < k || a < queue.peek()) {
                queue.offer(a);
            }

            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] top = new int[k];
        int index = 0;

        while (!queue.isEmpty()) {
            top[index] = queue.poll();
            index++;
        }
        return top;
    }

    public int[] getTopN2(int[] array, int k) {

        if (array.length == 0) {
            return new int[0];
        }

        int[] result = new int[k];
        partitionArray(array, 0, array.length - 1, k);

        for (int i = 0; i < result.length; i++) {
            result[i] = array[i];
        }

        return result;
    }

    private void partitionArray(int[] array, int start, int end, int k) {

        int m = partition(array, start, end);

        while (k != m) {
            if (k > m) {
                m = partition(array, m + 1, end);
            } else {
                m = partition(array, start, m - 1);
            }
        }
    }

    private int partition(int[] array, int start, int end) {

        int target = array[start];

        int i = start;
        int j = end;

        while (i != j) {
            while (j > i && array[j] >= target) {
                j--;
            }
            while (j > i && array[i] <= target) {
                i++;
            }
            if (j > i) {
                ExtenisonsKt.exch(array, i, j);
            }
        }

        ExtenisonsKt.exch(array, start, i);

        return i;
    }

    public static void main(String[] args) {

        TopN top = new TopN();
        int[] a = top.getTopN2(new int[]{5, 4, 1, 3, 6, 2, 9}, 3);

        System.out.println(Arrays.toString(a));
    }
}
