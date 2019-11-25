package com.qibenyu.algorithm


interface IAlgorithm {

    fun problem(): String

    fun condition(): String {
       return ""
    }

    fun answer(): String {
        return ""
    }

    fun thought(): String {
        return ""
    }
}