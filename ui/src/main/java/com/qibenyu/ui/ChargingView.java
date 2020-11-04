package com.qibenyu.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class ChargingView extends View implements IShowable {
    private static final String TAG = "ChargingView";

    private Paint mPaint;

    public float getOvalRotate() {
        return ovalRotate;
    }

    public void setOvalRotate(float ovalRotate) {
        this.ovalRotate = ovalRotate;
        invalidate();
    }

    private float ovalRotate = 0f;

    public ChargingView(Context context) {
        super(context);
    }

    public ChargingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChargingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int centerX;
    int centerY;
    float radius;

    int heightDiff = 100;

    int strokeWidth = 40;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        Log.d(TAG, "onSizeChanged: w = " + w + " , h = " + h);

        centerX = w / 2;
        centerY = h / 2;

        radius = Math.min(w, h) / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(getContext().getColor(R.color.blue_1));
        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(ovalRotate);
        canvas.drawOval(
                -radius + getPaddingStart() + strokeWidth / 2f,
                -radius + heightDiff / 2f + getPaddingTop() + strokeWidth / 2f,
                radius - getPaddingEnd() - strokeWidth / 2f,
                radius - heightDiff / 2f - getPaddingBottom() - strokeWidth / 2f,
                mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(ovalRotate + 10);
        canvas.drawOval(
                -radius + getPaddingStart() + strokeWidth / 2f,
                -radius + heightDiff / 2f + getPaddingTop() + strokeWidth / 2f,
                radius - getPaddingEnd() - strokeWidth / 2f,
                radius - heightDiff / 2f - getPaddingBottom() - strokeWidth / 2f,
                mPaint);
        canvas.restore();

//        mPaint.setColor(getContext().getColor(R.color.algae_green));
//        canvas.save();
//        canvas.translate(centerX, centerY);
//        canvas.rotate(ovalRotate + 90);
//        canvas.drawOval(
//                -radius + getPaddingStart() + strokeWidth / 2f,
//                -radius + heightDiff / 2f + getPaddingTop() + strokeWidth / 2f,
//                radius - getPaddingEnd() - strokeWidth / 2f,
//                radius - heightDiff / 2f - getPaddingBottom() - strokeWidth / 2f,
//                mPaint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(centerX, centerY);
//        canvas.rotate(ovalRotate + 100);
//        canvas.drawOval(
//                -radius + getPaddingStart() + strokeWidth / 2f,
//                -radius + heightDiff / 2f + getPaddingTop() + strokeWidth / 2f,
//                radius - getPaddingEnd() - strokeWidth / 2f,
//                radius - heightDiff / 2f - getPaddingBottom() - strokeWidth / 2f,
//                mPaint);
//        canvas.restore();
    }

    @Override
    public void bind(@NotNull ViewGroup viewGroup) {
        this.setPadding(20, 20, 20, 20);
        viewGroup.addView(this);
    }

    @Override
    public void show() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "ovalRotate", 0, 360);
        animator.setDuration(3000);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

    }
}



