package com.qibenyu.algorithm


class RomanNumber : IAlgorithm {

    override fun problem(): String {
        return "罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。"
    }

    override fun condition(): String {
        return "XXVII"
    }

    override fun answer(): String {
        return getRomanNumber("XXVII").toString()
    }

    val map = hashMapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    private fun getRomanNumber(s: String): Int {

        var count = 0
        val charArray = s.toCharArray()

        var preNum: Int? = map[charArray[0]]
        for (c in 1 until charArray.size) {

            val i = map[charArray[c]] ?: return 0

            if (preNum == null) return 0
            if (preNum >= i) {
                count += preNum
            } else {
                count -= preNum
            }
            preNum = i

        }
        if (preNum != null) {
            count += preNum
        }
        return count

    }

}