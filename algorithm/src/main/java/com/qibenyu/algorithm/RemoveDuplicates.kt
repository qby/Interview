package com.qibenyu.algorithm


/**
 * 删除排序数组中的重复项
 */
class RemoveDuplicates : IAlgorithm {
    override fun problem(): String {
        return ""
    }

    override fun condition(): String {
        return ""
    }

    override fun answer(): String {
        return ""
    }

    private fun removeDuplicates(array: IntArray) {

        var pre = array[0]
        for (i in 1 until array.size) {

            if (pre == array[i]) {

            }

            pre = array[i]
        }
    }
}