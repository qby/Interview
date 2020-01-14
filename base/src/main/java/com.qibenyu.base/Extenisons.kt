package com.qibenyu.base

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

const val TAG: String = "BaronQi"

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    )
}

fun Int.dp(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
}

fun View.bind2ViewGroup(viewGroup: ViewGroup) {
    layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    viewGroup.addView(this)
}

fun IntArray.print(): String {

    val sb = StringBuilder()
    for (i in this.indices) {
        sb.append(this[i])

        if (i != this.size - 1) sb.append(", ")
    }
    return sb.toString()

}

fun IntArray.exch(firstIndex: Int, secondIndex: Int) {
    val t = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = t
}