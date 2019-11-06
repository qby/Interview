package com.qibenyu.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qibenyu.base.dp2px


class AvatarView(context: Context, attr: AttributeSet) : View(context, attr) {

    private val bitmap = getAvatar(dp2px(200f).toInt())

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private lateinit var camera: Camera

    var point1 = Point(200, 200)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        camera = Camera()


        camera.setLocation(0f,0f, - 6 * resources.displayMetrics.density)

    }

    private var dregee: Float = 0.0f
        set(value) {
            field = value
            invalidate()
        }
    private var topFlip: Float = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    private var bottomFlip: Float = 0.0f
        set(value) {
            field = value
            invalidate()
        }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val centerX = point1.x + bitmapWidth / 2
        val centerY = point1.y + bitmapHeight / 2

        canvas?.let {

            it.save()
            it.translate(centerX.toFloat(), centerY.toFloat())
            it.rotate(dregee)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()

            it.clipRect(-bitmapWidth, -bitmapHeight, bitmapWidth, 0)
            it.rotate(-dregee)
            it.translate((-centerX).toFloat(), (-centerY).toFloat())
            it.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
            it.restore()


            it.save()
            it.translate(centerX.toFloat(), centerY.toFloat())
            it.rotate(dregee)
            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()

            it.clipRect(-bitmapWidth, 0, bitmapWidth, bitmapHeight)
            it.rotate(-dregee)
            it.translate((-centerX).toFloat(), (-centerY).toFloat())
            it.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
            it.restore()

        }

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