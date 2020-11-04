package com.qibenyu.explore.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TouchLayout extends FrameLayout {

    private static final String TAG = "Touch1Layout";

    public TouchLayout(Context context) {
        super(context);
    }

    public TouchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: " + (ev.getActionMasked() == MotionEvent.ACTION_DOWN));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: ev = " + (ev.getAction() == MotionEvent.ACTION_DOWN));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + (event.getActionMasked() == MotionEvent.ACTION_MOVE));
        return super.onTouchEvent(event);
    }
}
