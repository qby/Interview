package com.qibenyu.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qibenyu.base.dp2px
import kotlin.math.cos
import kotlin.math.sin


class DashView(context: Context, attr: AttributeSet) : View(context, attr), IShowable {

    private lateinit var paint: Paint
    private lateinit var dash: Path
    private lateinit var path: Path

    lateinit var effect: PathDashPathEffect

    companion object {
        val RADIUS = dp2px(150f)
        const val ANGLE = 120f
        val LENGTH = dp2px(100f)
        const val TAG = "DashView"
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path = Path()
        path.addArc(
            (width / 2 - RADIUS),
            (height / 2 - RADIUS),
            (width / 2 + RADIUS),
            (height / 2 + RADIUS),
            (90 + ANGLE / 2), 360f - ANGLE
        )

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dp2px(2f)


        dash = Path()
        dash.addRect(0f, 0f, dp2px(2f), dp2px(10f), Path.Direction.CW)

        val measure = PathMeasure(path, false)
        effect = PathDashPathEffect(
            dash,
            (measure.length - dp2px(2f)) / 20,
            0f,
            PathDashPathEffect.Style.ROTATE
        )

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.drawArc(
            (width / 2 - RADIUS),
            (height / 2 - RADIUS),
            (width / 2 + RADIUS),
            (height / 2 + RADIUS),
            (90 + ANGLE / 2), 360f - ANGLE, false
            , paint
        )

        paint.pathEffect = effect

        canvas?.drawArc(
            (width / 2 - RADIUS),
            (height / 2 - RADIUS),
            (width / 2 + RADIUS),
            (height / 2 + RADIUS),
            (90 + ANGLE / 2), 360f - ANGLE, false
            , paint
        )

        paint.pathEffect = null

        canvas?.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            ((cos(Math.toRadians(getAngleFromMark(6)))) * LENGTH).toFloat() + width / 2,
            ((sin(Math.toRadians(getAngleFromMark(6)))) * LENGTH).toFloat() + height / 2,
            paint
        )
    }

    private fun getAngleFromMark(mark: Int): Double {

        require(mark <= 20) {"error mark"}

        return ((90 + ANGLE / 2) + ((360 - ANGLE) / 20) * mark).toDouble()
    }

}
