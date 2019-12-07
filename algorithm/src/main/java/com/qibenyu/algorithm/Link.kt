package com.qibenyu.algorithm

data class Node(val value: Int, var next: Node? = null)

fun Node?.print(): String {
    var head = this
    val sb = StringBuilder()
    while (head != null) {

        sb.append(head.value)
        sb.append(",")
        head = head.next
    }

    return sb.toString()
}
