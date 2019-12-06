package com.qibenyu.ui

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import com.qibenyu.base.TAG
import com.qibenyu.base.bind2ViewGroup
import com.qibenyu.base.dp2px
import kotlin.random.Random


class MuahView(context: Context) : View(context), IShowable {

    private lateinit var paint: Paint

    private var animatorY: Float = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    var randomX: Float = 0.0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = dp2px(20f)

        val keyFrame = Keyframe.ofFloat(0f, 0f)
        val keyFrame1 = Keyframe.ofFloat(0.5f, height / 2f)
        val keyFrame2 = Keyframe.ofFloat(1f, height / 4 * 3f)
        val propertyValuesHolder =
            PropertyValuesHolder.ofKeyframe("animatorY", keyFrame, keyFrame1, keyFrame2)
        val animator = ObjectAnimator.ofPropertyValuesHolder(this, propertyValuesHolder)
        animator.duration = 4000
        animator.interpolator = BounceInterpolator()
        animator.start()
        randomX = Random.nextInt(width).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawText("\uD83D\uDE18", randomX, animatorY, paint)

        canvas?.drawLine(0f, height / 2f, width.toFloat(), height / 2f + 4, paint)

        canvas?.drawLine(0f, height / 4 * 3f, width.toFloat(), height / 4 * 3f + 4, paint)
    }

    override fun bind(viewGroup: ViewGroup) {
        bind2ViewGroup(viewGroup)
    }

    override fun show() {

        Log.d(TAG, "height = $height")
    }
}