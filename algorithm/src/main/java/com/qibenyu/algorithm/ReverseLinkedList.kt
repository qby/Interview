package com.qibenyu.algorithm

/**
 * 如何判断是这种link
 *  1 -> 2 -> 6 -> 9 -> 6 -> 2 -> 1
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */

data class Node(val value: Int, var next: Node?)

class ReverseLinkedList : IAlgorithm {

    override fun problem(): String {
        return "反转LinkList \n" +
                "1 -> 2 -> 6 -> 9 -> 6 -> 2 -> 1\n"
    }

    override fun condition(): String {
        return "1 -> 2 -> 6 -> 9 -> 6 -> 2 -> 1"
    }

    override fun answer(): String {

        val node3 = Node(3,null)
        val node2 = Node(2,node3)
        val node1 = Node(1,node2)


        var node = getReverseNode(node1)

//        while (node?.next != null) {
//
//            val value = node.value
//
//        }

//        val node = Node(0,null)
//
//        node.next = node1

        val sb = StringBuilder()
        while (node != null) {

            sb.append(node.value)
            node = node.next
        }

        return sb.toString()
    }

    fun getReverseNode(node: Node?): Node? {


        var head = node
        var nextNode: Node? = null
        var preNode: Node? = null

        while (head != null) {
            nextNode = head.next
            head.next = preNode
            preNode = head
            head = nextNode
        }

        return preNode

    }

}
