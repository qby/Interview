package com.qibenyu.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.qibenyu.base.extension.dp
import com.qibenyu.base.extension.isRTL
import java.util.*

data class Step(val content: String)
class StepView(context: Context, attr: AttributeSet) : View(context, attr), IShowable {

    private val stepTextPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val contentTextPaint: TextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    private val circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val dotSize: Float = 20.dp()

    private val lineSpace: Float = 44.dp()

    private val rect: Rect

    var list: List<Step> = Collections.emptyList()

    private val isRTL = context.isRTL()

    init {

        circlePaint.color = context.getColor(R.color.blue_1)

        stepTextPaint.color = Color.WHITE
        stepTextPaint
        stepTextPaint.style = Paint.Style.FILL
        stepTextPaint.textAlign = Paint.Align.CENTER
        stepTextPaint.textSize = 14.dp()

        contentTextPaint.textSize = 14.dp()

        rect = Rect()
    }

    fun setSteps(steps: List<Step>) {
        list = steps
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val height = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(
            getDefaultSize(suggestedMinimumWidth, widthMeasureSpec),
            getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var lineStart = 0f

        val dotX: Float
        val textX: Float
        if (isRTL) {
            dotX = width - dotSize / 2
            textX = dotSize - 10.dp()
        } else {
            dotX = dotSize / 2
            textX = dotSize + 10.dp()
        }

        for (step in list.indices) {

            canvas?.drawCircle(dotX, dotSize / 2 + lineStart, dotSize / 2, circlePaint)

            if (step < list.size - 1) {
                canvas?.drawRect(
                    dotX - 2.dp(), dotSize + lineStart,
                    dotX + 2.dp(), (step + 1) * lineSpace + dotSize, circlePaint
                )
            }

            stepTextPaint.getTextBounds(step.toString(), 0, 1, rect)
            var offset = (rect.top + rect.bottom) / 2
            canvas?.drawText(step.toString(), dotX, dotSize / 2 + lineStart - offset, stepTextPaint)

            val content = list[step].content
            canvas?.save()
            canvas?.translate(textX, lineStart)
            val staticLayout = StaticLayout(
                content,
                0,
                content.length,
                contentTextPaint,
                (width - dotSize - 10.dp()).toInt(),
                Layout.Alignment.ALIGN_NORMAL,
                1f,
                0f,
                false
            )
            staticLayout.draw(canvas)
            canvas?.restore()

            lineStart += lineSpace
        }

    }

    override fun bind(viewGroup: ViewGroup) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}