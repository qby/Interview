package com.qibenyu.algorithm


class IPAddress : IAlgorithm {
    override fun problem(): String {

        return "IP 地址 1.1.1.1 -> 1[.]1[.]1[.]1[.]"
    }

    override fun condition(): String {
        return "123.122.111.0"
    }

    override fun answer(): String {
        val ip = "123.122.111.0"
        return ip.replace(".","[.]")
    }

}