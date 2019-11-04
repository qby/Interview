package com.qibenyu.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class AvatarView(context: Context, attr: AttributeSet) : View(context, attr) {

    private val bitmap = getAvatar(400)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private val rectF = RectF()


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        rectF.set(200f, 200f, 200 + 400f, 200 + 400f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val id = canvas?.saveLayer(rectF, paint)
        canvas?.drawOval(200f, 200f, 200 + 400f, 200 + 400f, paint)
        paint.xfermode = xfermode

        canvas?.drawBitmap(bitmap, 200f, 200f, paint)
        paint.xfermode = null
        id?.let { canvas.restoreToCount(it) }

    }

    private fun getAvatar(width: Int): Bitmap {

        val option = BitmapFactory.Options()

        option.inJustDecodeBounds = true

        BitmapFactory.decodeResource(resources, R.drawable.bg_motto_1, option)

        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width

        return BitmapFactory.decodeResource(resources, R.drawable.bg_motto_1, option)

    }

}