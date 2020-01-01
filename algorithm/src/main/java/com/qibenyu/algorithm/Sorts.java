package com.qibenyu.algorithm;

import com.qibenyu.base.ExtenisonsKt;

import org.jetbrains.annotations.NotNull;

public class Sorts implements IAlgorithm {
    @NotNull
    @Override
    public String problem() {
        return "排序";
    }

    @NotNull
    @Override
    public String condition() {
        return "{5, 3, 4, 6, 2, 8, 7, 1}";
    }

    @NotNull
    @Override
    public String thought() {
        return "多种排序，思路见注释" +
                "1.选择排序 \n" +
                "2.插入排序 \n" +
                "3.希尔排序 \n" +
                "4.快速排序 \n";
    }

    @NotNull
    @Override
    public String answer() {
        int[] array = {5, 3, 4, 6, 2, 8, 7, 1};
//        quickSort(array, 0, array.length - 1);
//        selectionSort(array);
//        insertSort(array);
        shellSort(array);
        return ExtenisonsKt.print(array);
    }

    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    ExtenisonsKt.exch(array, i, j);
                }
            }
        }
    }

    private void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && (array[j] < array[j - 1]); j--) {
                ExtenisonsKt.exch(array, j, j - 1);
            }
        }
    }


    private void shellSort(int[] array) {
        int gap = 4;
        while (gap >= 1) {
            for (int i = gap; i < array.length; i += gap) {
                for (int j = i; j > 0 && (array[j] < array[j - gap]); j -= gap) {
                    ExtenisonsKt.exch(array, j, j - gap);
                }
            }
            gap /= 2;
        }
    }

    /**
     * 快速排序：把第0个位置作为基准位.\n " +
     * "由最后向前进行比较,找到比基准位小的数,\n" +
     * "由第一个位置向后进行比较,如果比基准位大,则交换\n" +
     * "此算法就是把基准位放到合适的位置,比他小的放到他前面,比他大的放在他后面\n" +
     * " 算法复杂度:\n" +
     * " 快速排序是不稳定的，其时间平均时间复杂度是O(nlgn)。
     *
     * @param array
     * @param start
     * @param end
     */
    private void quickSort(int[] array, int start, int end) {

        if (start > end) return;

        int i = start;
        int j = end;

        int x = array[start];

        while (i != j) {

            while (j > i && array[j] >= x) {
                j--;
            }
            while (i < j && array[i] <= x) {
                i++;
            }

            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i];
        array[i] = array[start];
        array[start] = temp;

        quickSort(array, start, i - 1);
        quickSort(array, j + 1, end);
    }

}