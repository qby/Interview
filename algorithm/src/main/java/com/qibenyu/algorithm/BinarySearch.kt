package com.qibenyu.algorithm


/**
 */
fun binarySearch(array: IntArray, target: Int): Int {


    var lo = 0
    var hi = array.size

    val mid = (hi - lo) / 2

    while (hi > lo) {

        if (target == array[mid]) {
            return mid
        }
        if (target > array[mid]) {
            lo = mid + 1
        } else {
            hi = mid - 1
        }
    }


}