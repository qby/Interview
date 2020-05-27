package com.qibenyu.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class QQStepView extends View {

    private Paint mOutPaint = null;
    public QQStepView(Context context) {
        super(context);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}
