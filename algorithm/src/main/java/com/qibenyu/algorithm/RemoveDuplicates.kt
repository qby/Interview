package com.qibenyu.algorithm

import com.qibenyu.base.extension.print


/**
 * 删除排序数组中的重复项
 */
class RemoveDuplicates : IAlgorithm {
    override fun problem(): String {
        return "删除排序数组中的重复项"
    }

    override fun condition(): String {
        return "[1,1,2,2,2,3,3,4,5,5,5,5,5,5,5,6]"
    }

    override fun answer(): String {
        val array = intArrayOf(1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 5, 5, 5, 5, 5, 6)
        removeDuplicates(array)
        return array.print()
    }

    private fun removeDuplicates(array: IntArray) {

        var preIndex = 0
        for (i in 1 until array.size) {

            if (array[preIndex] == array[i]) {
                continue
            }

            array[preIndex + 1] = array[i]
            preIndex ++

        }
    }
}