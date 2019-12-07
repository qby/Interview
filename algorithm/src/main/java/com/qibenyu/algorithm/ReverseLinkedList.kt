package com.qibenyu.algorithm

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

        val node7 = Node(1, null)
        val node6 = Node(2, node7)
        val node5 = Node(7, node6)
        val node4 = Node(8, node5)
        val node3 = Node(7, node4)
        val node2 = Node(2, node3)
        val node1 = Node(1, node2)

        var node = getReverseNode(node1)

        return node.toString()
    }


    private fun getReverseNode(node: Node?): Boolean {

        var slow: Node? = node
        var fast: Node? = node?.next
        var nextNode: Node?
        var preNode: Node? = null

        while (fast != null && fast.next != null) {

            nextNode = slow?.next

            slow?.next = preNode

            preNode = slow

            slow = nextNode

            fast = fast.next?.next
        }

        var a = slow?.next
        if (fast == null) {
            slow = preNode
        }

        while (slow != null) {
            if (a?.value == slow.value) {
                a = a.next
                slow = slow.next
            } else {
                return false
            }
        }
        return true

    }

}
