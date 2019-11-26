package com.qibenyu.algorithm


interface IAlgorithm {

    fun problem(): String

    fun condition(): String

    fun thought(): String {
        return ""
    }

    fun answer(): String

}