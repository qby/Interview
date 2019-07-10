package com.qibenyu.algorithm


/**
 */
fun binarySearch(array: IntArray, target: Int): Int {


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