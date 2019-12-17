package com.qibenyu.algorithm

/**
删除单链表的倒数第N个元素
https://www.iteye.com/blog/lueye-2176940
 */

class RemoveNthFromEnd : IAlgorithm {
    override fun problem(): String {
        return "删除单链表的倒数第N个元素"
    }

    override fun condition(): String {
        return "(1 -> 2 -> 7 -> 8 -> 7 -> 2 -> 1) , n = 3"
    }

    override fun thought(): String {
        return "使用快慢指针，fast先走到N，然后slow和fast一同前进"
    }

    override fun answer(): String {
        val node7 = Node(1, null)
        val node6 = Node(2, node7)
        val node5 = Node(7, node6)
        val node4 = Node(8, node5)
        val node3 = Node(7, node4)
        val node2 = Node(2, node3)
        val node1 = Node(1, node2)

        return removeFromEnd2(node1, 3)
    }

    private fun removeFromEnd(first: Node?, n: Int): String {
        if (first == null) {
            return ""
        }

        var count = 1
        var node = first
        while (node?.next != null) {
            node = node.next
            count++
        }

        var deleteNode: Node? = first

        for (i in 0 until count - n - 1) {
            deleteNode = deleteNode?.next!!
        }

        deleteNode?.next = deleteNode?.next?.next

        return first.printLink()
    }

    private fun removeFromEnd2(first: Node?, n: Int) : String {
        var fast = first
        var slow = first

        var step = 0
        while (fast?.next != null) {
            fast = fast.next
            if (step >= n) {
                slow = slow?.next
            }

            step++

        }

        slow?.next = slow?.next?.next

        return first.printLink()

    }
}