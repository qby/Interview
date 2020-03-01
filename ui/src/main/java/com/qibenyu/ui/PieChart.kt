package com.qibenyu.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.qibenyu.base.extension.bind2ViewGroup
import com.qibenyu.base.extension.dp2px
import kotlin.math.cos
import kotlin.math.sin


class PieChart(context: Context) : View(context), IShowable {
    lateinit var paint: Paint

    lateinit var path: Path


    private val degrees = floatArrayOf(60f, 100f, 120f, 80f)
    private val colors = intArrayOf(
        Color.parseColor("#2979FF"), Color.parseColor("#C2185B"),
        Color.parseColor("#009688"), Color.parseColor("#FF8F00")
    )

    companion object {
        val TAG = "Pie"
        val RADIUS = dp2px(150f)
        val LENGTH = dp2px(10f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paint = Paint(Paint.ANTI_ALIAS_FLAG)

        path = Path()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var startAngle = 0f
        for (i in degrees.indices) {

            canvas?.save()
            if (i == 1) {
                canvas?.translate(
                    (cos(Math.toRadians((startAngle + degrees[i] / 2).toDouble())) * LENGTH).toFloat(),
                    (sin(Math.toRadians((startAngle + degrees[i] / 2).toDouble())) * LENGTH).toFloat()
                )
            }

            paint.color = colors[i]
            canvas?.drawArc(
                width / 2 - RADIUS, height / 2 - RADIUS,
                width / 2 + RADIUS, height / 2 + RADIUS,
                startAngle, degrees[i], true, paint
            )

            canvas?.restore()
            Log.d(TAG, "pie chart index = $i , start = $startAngle , end = $")

            startAngle += degrees[i]
        }

    }

    override fun show() {
    }

    override fun bind(viewGroup: ViewGroup) {
        bind2ViewGroup(viewGroup)
    }

}