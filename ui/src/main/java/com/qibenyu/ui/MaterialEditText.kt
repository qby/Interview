package com.qibenyu.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.EditText


class MaterialEditText(context: Context) : EditText(context)
    , IShowable {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun bind(viewGroup: ViewGroup) {

    }

    override fun show() {
    }

}