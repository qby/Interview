package com.qibenyu.algorithm

data class Node(val value: Int, var next: Node? = null)

fun print(node: Node?): String {
    var head = node
    val sb = StringBuilder()
    while (head != null) {

        sb.append(head.value)
        sb.append(",")
        head = head.next
    }

    return sb.toString()
}
