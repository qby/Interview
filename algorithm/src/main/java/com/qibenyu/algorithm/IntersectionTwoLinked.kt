package com.qibenyu.algorithm

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
class IntersectionTwoLinked : IAlgorithm {

    override fun problem(): String {
        return "编写一个程序，找到两个单链表相交的起始节点。"
    }

    override fun condition(): String {
        return "链表1 : 1->2->7->8->7->2->1 \n" +
                "链表2: 1->2->7->8"
    }

    override fun answer(): String {
        val node7 = Node(1, null)
        val node6 = Node(2, node7)
        val node5 = Node(7, node6)
        val node4 = Node(8, node5)
        val node3 = Node(7, node4)
        val node2 = Node(2, node3)
        val node1 = Node(1, node2)

        val node11 = Node(1, null)
        val node10 = Node(2, node11)
        val node9 = Node(7, node10)
        val node8 = Node(8, node9)

        val a = getIntersectionNode(node1, node8)

        return "$a"
    }

    private fun getIntersectionNode(node1: Node?, node2: Node?): Int? {
        if (node1 == null || node2 == null) {
            return null
        }

        var nodeF = node1
        var nodeS = node2

        var pre: Node? = null
        while (nodeF != null) {
            val next = nodeF.next
            nodeF.next = pre
            pre = nodeF
            nodeF = next
        }

        while (nodeS != null) {
            val next = nodeS.next
            nodeS.next = pre
            pre = nodeS
            nodeS = next
        }

        var same: Int? = null

        while (nodeS != null && nodeF != null) {
            if (nodeF.value == nodeS.value) {
                same = nodeF.value
            }
            nodeS = nodeS.next
            nodeF = nodeF.next
        }

        return same
    }

}