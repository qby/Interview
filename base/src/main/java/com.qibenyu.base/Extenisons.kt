package com.qibenyu.base

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup


fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
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