package com.qibenyu.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.qibenyu.ui.util.BitmapUtil;

import org.jetbrains.annotations.NotNull;

public class MultiTouchView extends View implements IShowable {

    private Bitmap mBitmap;
    private Paint mPaint;
    private float offsetX;
    private float offsetY;
    private float downX;
    private float downY;
    private float upX;
    private float upY;
    private float originalOffsetY;
    private float originalOffsetX;

    public MultiTouchView(Context context) {
        super(context);
        mBitmap = BitmapUtil.getContent(getResources(), 500);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = originalOffsetX + event.getX() - downX;
                offsetY = originalOffsetY + event.getY() - downY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
//                upX = event.getX();
//                upY = event.getY();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);
    }

    @Override
    public void bind(@NotNull ViewGroup viewGroup) {

        viewGroup.addView(this);
    }

    @Override
    public void show() {

    }
}
