package com.qibenyu.algorithm


/**
 */
class BinarySearch : IAlgorithm {


    private fun binarySearch(array: IntArray, target: Int): Int {

        var lo = 0
        var hi = array.size - 1

        while (lo <= hi) {

            var mid = lo + (hi - lo) / 2

            println("mid = $mid")
            when {
                target > array[mid] -> lo = mid + 1
                target < array[mid] -> hi = mid - 1
                else -> return mid
            }
        }

        return -1

    }

    override fun problem(): String {
        return "二分查找"
    }

    override fun condition(): String {
        return "数组"
    }

    override fun anwser(): String {
        return binarySearch(intArrayOf(1, 2, 3, 4, 5, 6), 6).toString()
    }
}

