package com.qibenyu.algorithm

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
class IntersectionTwoLinked : IAlgorithm {

    override fun problem(): String {
        return "编写一个程序，找到两个单链表相交的起始节点。"
    }

    override fun condition(): String {
        return "链表1 : 4->1->8->4->5 \n" +
                "链表2: 5->0->1->8->4->5"
    }

    override fun answer(): String {
        val node5 = Node(5, null)
        val node4 = Node(4, node5)
        val node3 = Node(8, node4)
        val node2 = Node(1, node3)
        val node1 = Node(4, node2)

        val node13 = Node(5, null)
        val node12 = Node(4, node13)
        val node11 = Node(8, node12)
        val node10 = Node(1, node11)
        val node9 = Node(0, node10)
        val node8 = Node(5, node9)

        val a = getIntersectionNode2(node1, node8)

        return a.printLink()
    }

    private fun getIntersectionNode2(node1: Node?, node2: Node?): Node? {
        if (node1 == null || node2 == null) {
            return null
        }

        var n1 = node1
        var n2 = node2
        while (n1 != n2) {
            n1 = if (n1 == null) {
                node2
            } else {
                n1.next
            }

            n2 = if (n2 == null) {
                node1
            } else {
                n2.next
            }
        }

        return n1

    }

    @Deprecated("")
    private fun getIntersectionNode(node1: Node?, node2: Node?): Int? {
        if (node1 == null || node2 == null) {
            return null
        }

        var nodeF = node1
        var nodeS = node2

        var pre1: Node? = null
        while (nodeF != null) {
            val next = nodeF.next
            nodeF.next = pre1
            pre1 = nodeF
            nodeF = next
        }

        var pre2: Node? = null
        while (nodeS != null) {
            val next = nodeS.next
            nodeS.next = pre2
            pre2 = nodeS
            nodeS = next
        }

        var same: Int? = null

        while (pre1 != null && pre2 != null) {
            if (pre1.value == pre2.value) {
                same = pre2.value
            }
            pre1 = pre1.next
            pre2 = pre2.next
        }

        return same
    }

}