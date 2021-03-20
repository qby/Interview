package com.qibenyu.algorithm

import android.util.SparseBooleanArray
import android.util.SparseIntArray


/**
 */
class BinarySearch : IAlgorithm {


    fun binarySearch(array: IntArray, target: Int): Int {

        var lo = 0;
        var hi = array.size - 1

        var mid = (hi + lo) / 2

        while (lo <= hi) {

            if (array[mid] == target) {
                return mid
            } else if (array[mid] > target) {
                hi = mid - 1
            } else {
                lo = mid + 1
            }
            mid = (hi + lo) / 2
        }

        return mid

    }

    override fun problem(): String {
        return "二分查找"
    }

    override fun condition(): String {
        return "数组 (1, 2, 3, 4, 5, 6)"
    }

    override fun answer(): String {
        return binarySearch(intArrayOf(1, 2, 3, 4, 5, 6), 6).toString()
    }
}

