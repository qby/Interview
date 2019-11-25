package com.qibenyu.algorithm


/**
 * 32位有符号整数进行反转
 * input 123
 * output 321
 *
 * input -123
 * output -321
 *
 * input 210
 * output 12
 */

class IntReverse : IAlgorithm {

    override fun problem(): String {
        return "32位有符号整数进行反转 \n" +
                "input 123 output 321 \n" +
                "input -123 output -321 \n" +
                "input 120 output 12 \n"
    }

    override fun condition(): String {
        return "123\n" +
                "-123\n" +
                "120\n" +
                "-3"
    }

    override fun answer(): String {
        val numbers = condition()
        val numberarray = numbers.split('\n')

        var append = ""
        for (s in numberarray) {
            append += reverse(s.toInt())
            append += "\n"
        }

        return append
    }

    override fun thought(): String {
        return "pop operation:\n" +
                "pop = x % 10;\n" +
                "x /= 10;\n" +
                "push operation:\n" +
                "temp = rev * 10 + pop;\n" +
                "rev = temp;\n"
    }

    private fun reverse(num: Int): Int {

        var rev = 0
        var n = num
        while (n != 0) {
            val pop = n % 10
            n /= 10

            if (rev > Int.MAX_VALUE / 10 || rev == Int.MAX_VALUE / 10 && pop > 7) return 0
            if (rev < Int.MIN_VALUE / 10 || rev == Int.MIN_VALUE && pop < -8) return 0

            rev = rev * 10 + pop
        }
        return rev
    }

}