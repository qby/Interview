package com.qibenyu.algorithm;

import com.qibenyu.base.extension.ExtenisonsKt;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

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
        quickSort(array, 0, array.length - 1);
//        selectionSort(array);
//        insertSort(array);
//        shellSort(array);
//        aux = new int[array.length];
//        mergeSort(array, 0, array.length - 1);
        return ExtenisonsKt.print(array);
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 4, 6, 2, 8, 7, 1};
        quickSort(array, 0, array.length - 1);

//        Arrays.toString(args);
        System.out.println(Arrays.toString(array));
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
    private static void quickSort(int[] array, int start, int end) {

        if (start > end) return;

        int left = start;
        int right = end;

        int target = array[start];

        while (left != right) {

            while (left < right && array[right] >= target) {
                right--;
            }

            while (left < right && array[left] <= target) {
                left++;
            }


            if (left < right) {
                ExtenisonsKt.exch(array, left, right);
            }
        }

        ExtenisonsKt.exch(array, start, left);

        quickSort(array, start, left - 1);
        quickSort(array, right + 1, end);

    }

    private int[] aux = null;

    private void mergeSort(int[] array, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int mid = (hi + lo) / 2;

        mergeSort(array, lo, mid);
        mergeSort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }

    private void merge(int[] array, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) array[k] = aux[j++];
            else if (j > hi) array[k] = aux[i++];
            else if (aux[j] < aux[i]) array[k] = aux[j++];
            else array[k] = aux[i++];
        }
    }

    public void heapSort(int[] array) {

        //1.构建大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(array, i, array.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = array.length - 1; j > 0; j--) {
            swap(array, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(array, 0, j);//重新对堆进行调整
        }

    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
