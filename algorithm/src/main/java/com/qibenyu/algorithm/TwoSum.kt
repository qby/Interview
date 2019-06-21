package com.qibenyu.algorithm

/**
 * Two Sum
 *  Example:
 *  Given nums = [2, 7, 11, 15], target = 9,
 *  Because nums[0] + nums[1] = 2 + 7 = 9,
 *  return [0, 1].
 */

fun twoSum(array: IntArray, target: Int): IntArray {

    /*
    for (i in array.indices) {

        for (j in array.indices) {
            if (array[i] + array[j] == target) {
                return arrayOf(i, j)
            }
        }

    }
    return null
    */

    val map = mutableMapOf<Int, Int>()
    for (i in array.indices) {
        if (map.containsKey(array[i])) {
            return intArrayOf(map[array[i]]!!, i)
        }
        map[target - array[i]] = i
    }
    return intArrayOf()
}
