package com.qibenyu.algorithm

import com.qibenyu.base.extension.print

/**
 * Two Sum
 *  Example:
 *  Given nums = [2, 7, 11, 15], target = 9,
 *  Because nums[0] + nums[1] = 2 + 7 = 9,
 *  return [0, 1].
 */

class TwoSum : IAlgorithm {
    override fun problem(): String {
        return "Given nums = 2, 7, 11, 15, target = 9,\n" +
                "Because nums[0] + nums[1] = 2 + 7 = 9,\n" +
                "return [0, 1]."
    }

    override fun condition(): String {
        return "2, 7, 11, 15, target = 9"
    }

    override fun answer(): String {
        val array = twoSum(intArrayOf(2, 7, 11, 15), 9)


        return array.print()
    }

    private fun twoSum(array: IntArray, target: Int): IntArray {

        val map = mutableMapOf<Int, Int>()

        for (i in array.indices) {
            if (map.containsKey(array[i])) {
                return intArrayOf(map[array[i]]!!, i)
            }
            map[target - array[i]] = i

        }
        return intArrayOf()
    }

}

