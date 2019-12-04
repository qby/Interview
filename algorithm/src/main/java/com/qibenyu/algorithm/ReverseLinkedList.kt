package com.qibenyu.algorithm

import java.util.*

/**
 * 如何判断是这种link
 *  1 -> 2 -> 6 -> 9 -> 6 -> 2 -> 1
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */

class ReverseLinkedList : IAlgorithm {

    override fun problem(): String {
        return "反转LinkList \n" +
                "1 -> 2 -> 6 -> 9 -> 6 -> 2 -> 1\n"
    }

    override fun condition(): String {
        return "1 -> 2 -> 6 -> 9 -> 6 -> 2 -> 1"
    }

    override fun answer(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun isReverseLinkedList(link: LinkedList<Int>): Boolean {

        for (i in link) {


        }
        return false

    }

}
