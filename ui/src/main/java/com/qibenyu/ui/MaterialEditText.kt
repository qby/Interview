package com.qibenyu.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.qibenyu.base.bind2ViewGroup
import com.qibenyu.base.dp2px


class MaterialEditText(context: Context) : EditText(context)
    , IShowable {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.textSize = dp2px(10f)
    }


    companion object {
        val PADDING = dp2px(20f).toInt()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setPadding(paddingLeft, (PADDING + paddingTop), paddingRight, paddingBottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!TextUtils.isEmpty(hint)) {
            canvas?.drawText(hint.toString(), 0f, 0f, paint)
        }
    }

    override fun bind(viewGroup: ViewGroup) {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        viewGroup.addView(this)
    }

    override fun show() {
        hint = "Username"
    }

}