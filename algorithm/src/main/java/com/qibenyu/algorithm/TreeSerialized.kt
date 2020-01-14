package com.qibenyu.algorithm


class TreeSerialized :IAlgorithm {
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

}