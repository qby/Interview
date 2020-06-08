package com.qibenyu.explore.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SplashView extends SurfaceView implements SurfaceHolder.Callback2 , Runnable{
    public SplashView(Context context) {
        super(context);
    }

    private SurfaceHolder mHolder = null;
    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this); // 为SurfaceView添加状态监听

    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    private Thread t;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        t = new Thread();
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {

        Canvas canvas = mHolder.lockCanvas();


    }
}

