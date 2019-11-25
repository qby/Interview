package com.qibenyu.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class TouchView(context: Context) : View(context) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    val bitmap = getAvatar(100)

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)
    }

    private fun getAvatar(width: Int): Bitmap {

        val option = BitmapFactory.Options()

        option.inJustDecodeBounds = true

        BitmapFactory.decodeResource(resources, R.drawable.maps, option)

        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width

        return BitmapFactory.decodeResource(resources, R.drawable.maps, option)

    }
}