package com.qibenyu.algorithm

import java.lang.StringBuilder


data class TreeNode(val value: String, var left: TreeNode? = null, var right: TreeNode? = null)

fun TreeNode?.serialized(): String {
    val sb = StringBuilder()
    serialized(this, sb)
    return sb.toString()
}

private fun serialized(treeNode: TreeNode?, sb: StringBuilder) {
    if (treeNode == null) {
        sb.append("#")
        return
    }
    sb.append(treeNode.value)
    serialized(treeNode.left, sb)
    serialized(treeNode.right, sb)
}

fun String.deserialized(): TreeNode? {
    return deserialized(Pointer(0), this)
}

data class Pointer(var index: Int = 0)

private fun deserialized(pointer: Pointer, treeSerialized: String): TreeNode? {

    if (treeSerialized[pointer.index] == '#') {
        pointer.index++
        return null
    }

    val treeNode = TreeNode(treeSerialized[pointer.index].toString())
    pointer.index++
    treeNode.left = deserialized(pointer, treeSerialized)
    treeNode.right = deserialized(pointer, treeSerialized)

    return treeNode
}

fun preOrderTraverse(node: TreeNode) {
    var root = node

}



