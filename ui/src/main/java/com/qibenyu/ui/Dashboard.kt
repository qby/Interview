package com.qibenyu.ui

import android.content.Context
import android.graphics.*
import android.view.View
import android.view.ViewGroup
import com.qibenyu.base.extension.bind2ViewGroup
import com.qibenyu.base.extension.dp
import kotlin.math.cos
import kotlin.math.sin


class Dashboard(context: Context) : View(context), IShowable {

    private lateinit var paint: Paint
    private lateinit var dash: Path
    private lateinit var path: Path

    private lateinit var effect: PathDashPathEffect

    companion object {
        val RADIUS = 150.dp()
        const val ANGLE = 120f
        val LENGTH = 100.dp()
        const val TAG = "Dashboard"
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
        paint.strokeWidth = 2.dp()


        dash = Path()
        dash.addRect(0f, 0f, 2.dp(), 10.dp(), Path.Direction.CW)

        val measure = PathMeasure(path, false)
        effect = PathDashPathEffect(
            dash,
            (measure.length - 2.dp()) / 20,
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

    override fun show() {

    }

    override fun bind(viewGroup: ViewGroup) {
        bind2ViewGroup(viewGroup)
    }


}
