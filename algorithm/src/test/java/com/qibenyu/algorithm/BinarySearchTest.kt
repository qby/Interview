package com.qibenyu.algorithm

import org.junit.Test

class BinarySearchTest {

    @Test
    fun binarySearch() {

        val bs = BinarySearch()

        val res = bs.binarySearch(intArrayOf(1, 2, 3, 4, 5, 6), 6)
        println(res)
    }
}