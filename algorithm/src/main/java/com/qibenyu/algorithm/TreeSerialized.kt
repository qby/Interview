package com.qibenyu.algorithm

import java.lang.StringBuilder
import java.util.*


class TreeSerialized : IAlgorithm {
    override fun problem(): String {
        return "二叉树的序列化与反序列化"
    }

    override fun condition(): String {
        return "ABC##FX###EG##DY##Z##"
    }

    override fun answer(): String {

        val root = "ABC##FX###EG##DY##Z##".deserialized()

        if (root != null) {
            preOrderTraverse(root)
        }

        return root.serialized()
    }

    private val sb: StringBuilder = StringBuilder()
    private fun dfs(node: TreeNode?) {

        if (node == null) {
            return
        }

        sb.append(node.value)

        dfs(node.left)
        dfs(node.right)

    }

    private fun dfs2(node:TreeNode?) {
        if (node == null) {
            return
        }
        val stack = Stack<TreeNode>()

        stack.push(node)

        while (!stack.isEmpty()) {

            val n = stack.pop()

            stack.push(n.left)
            stack.push(n.right)
        }

    }

    private fun bfs(node: TreeNode? ) {
        if (node == null) {
            return
        }

        val queue : Queue<TreeNode> = ArrayDeque<TreeNode>()

        queue.add(node)

        while(!queue.isEmpty()) {

            val node = queue.remove()

            print(node.value)

            if (node.left != null) {
                queue.offer(node.left)
            }
            if (node.right != null) {
                queue.offer(node.right)
            }


        }



    }

}