package com.qibenyu.explore.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.qibenyu.explore.R;

public class TouchActivity extends Activity {

    private static final String TAG = "Touch1Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: " + (ev.getActionMasked() == MotionEvent.ACTION_DOWN));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + (event.getActionMasked() == MotionEvent.ACTION_MOVE));
        return super.onTouchEvent(event);
    }
}
