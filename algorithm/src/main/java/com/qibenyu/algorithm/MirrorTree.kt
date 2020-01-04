package com.qibenyu.algorithm


class MirrorTree : IAlgorithm {

    override fun problem(): String {
        return "镜像二叉树"
    }

    override fun condition(): String {
        return "前序二叉树：ABC##FX###EG##DY##Z##"
    }

    override fun answer(): String {

        val root = "ABC##FX###EG##DY##Z##".deserialized()
        reverse(root)

        return root.serialized()
    }

    private fun reverse(root: TreeNode?) {
        if(root == null) {
            return
        }

        if (root.left == null
            && root.right == null) {
            return
        }

        val node = root.right

        root.right = root.left

        root.left = node

        reverse(root.left)
        reverse(root.right)


    }

}