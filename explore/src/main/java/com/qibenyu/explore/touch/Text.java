package com.qibenyu.explore.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Text extends androidx.appcompat.widget.AppCompatTextView {

    private static final String TAG = "Touch1";

    public Text(Context context) {
        super(context);
    }

    public Text(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Text(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent: " + (event.getActionMasked() == MotionEvent.ACTION_DOWN));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + (event.getActionMasked() == MotionEvent.ACTION_DOWN));
        return super.onTouchEvent(event);
    }
}
