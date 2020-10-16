package com.qibenyu.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ChargingView extends View {

    private Paint mPaint;

    public ChargingView(Context context) {
        super(context);
    }

    public ChargingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChargingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float centerX;
    float centerY;
    float radius;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        centerX = w / 2f;
        centerY = h / 2f;

        radius = Math.min(centerX, centerY);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();
        canvas.rotate(10);
        canvas.drawOval(centerX - radius, centerY - radius,
                centerX + radius, centerY + radius, mPaint);
        canvas.restore();

    }
}



