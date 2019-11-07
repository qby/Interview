package com.qibenyu.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import com.qibenyu.base.dp2px


class MaterialEditText(context: Context) : EditText(context)
    , IShowable, TextWatcher {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.textSize = dp2px(10f)
    }

    private var hintAlpha = 0f
        set(value) {
            field = value
            invalidate()
        }

    companion object {
        val PADDING = dp2px(10f).toInt()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setPadding(paddingLeft, (PADDING + paddingTop), paddingRight, paddingBottom)

        addTextChangedListener(this)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    private val OFFSET  = 10
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.alpha = (0xff * hintAlpha).toInt()
        val extraOffset = OFFSET * (1 - hintAlpha)
        canvas?.drawText(hint.toString(), dp2px(5f), dp2px(14f) + extraOffset, paint)
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    private var drawFlag: Boolean = false

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {

        if (!TextUtils.isEmpty(text) && !drawFlag) {
            drawFlag = true
            getAnimator().start()
        } else if (TextUtils.isEmpty(text) && drawFlag) {
            drawFlag = false
            getAnimator().reverse()

        }
    }

    private fun getAnimator(): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(this, "hintAlpha", 1f)
        objectAnimator.duration = 500
        return objectAnimator
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