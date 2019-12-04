package com.qibenyu.algorithm


class PalindromeNumber : IAlgorithm {

    override fun problem(): String {
        return "判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。"
    }

    override fun condition(): String {
        return "12321"
    }

    override fun answer(): String {
        return isPalindromeNumber2(12321).toString()
    }

    @Deprecated(message = "需要额外空间")
    fun isPalindromeNumber(number: Int): Boolean {

        val numberStr = number.toString()

        val l = numberStr.length / 2

        for (i in 0..l) {
            if (numberStr[i] != numberStr[numberStr.length - 1 - i]) {
                return false
            }
        }
        return true

    }

    fun isPalindromeNumber2(number: Int): Boolean {
        var rev = 0
        var n = number
        while (n != 0) {
            val pop = n % 10
            n /= 10
            rev = rev * 10 + pop
        }

        return rev == number

    }

}