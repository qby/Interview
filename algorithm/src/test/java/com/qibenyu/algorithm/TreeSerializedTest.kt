package com.qibenyu.algorithm

import org.junit.Assert.assertEquals
import org.junit.Test

class TreeSerializedTest {

    @Test
    fun answer() {

        val root = "ABC##FX###EG##DY##Z##".deserialized()

        if (root != null) {
            preOrderTraverse(root)
        }
        assertEquals(1, 1)
    }
}