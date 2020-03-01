package com.qibenyu.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.qibenyu.base.extension.ExtenisonsKt;
import com.qibenyu.ui.util.BitmapUtil;

import org.jetbrains.annotations.NotNull;

public class ScalableImageView extends View
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, Runnable, IShowable {

    private static final String TAG = "ScalableImageView";

    private GestureDetectorCompat mDetector;
    private ScaleGestureDetector mScaleDetector;
    private ViewConfiguration viewConfiguration;
    private OverScroller mOverScroller;
    private Bitmap mBitmap;
    private Paint mPaint;

    private float bigScale;
    private float smallScale;

    private boolean normal = true;

    private float originalOffsetX = 0;
    private float originalOffsetY = 0;

    private float offsetX = 0;
    private float offsetY = 0;

    private float percent;

    public ScalableImageView(Context context) {
        super(context);
        init();
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapUtil.getContent(getResources(), (int) ExtenisonsKt.dp(200));
        mDetector = new GestureDetectorCompat(getContext(), this);
        mDetector.setIsLongpressEnabled(false);
        mOverScroller = new OverScroller(getContext());
        viewConfiguration = ViewConfiguration.get(getContext());
        viewConfiguration.getScaledMinimumFlingVelocity();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        originalOffsetX = (w - mBitmap.getWidth()) / 2f;
        originalOffsetY = (h - mBitmap.getHeight()) / 2f;

        if ((float) mBitmap.getWidth() / w > (float) mBitmap.getHeight() / h) {
            bigScale = (float) h / mBitmap.getHeight();
            smallScale = (float) w / mBitmap.getWidth();
        } else {
            bigScale = (float) w / mBitmap.getWidth();
            smallScale = (float) h / mBitmap.getHeight();
        }

        bigScale *= 1.5;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scale = smallScale + (bigScale - smallScale) * percent;
        canvas.translate(offsetX * percent, offsetY * percent);
        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(mBitmap, originalOffsetX, originalOffsetY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    // ACTION_DOWN 必须返回true
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    // 预按下 , 100ms 到达
    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    // 单击 , 返回值无用
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    // onMove
    @Override
    public boolean onScroll(MotionEvent downEvent, MotionEvent event, float distanceX, float distanceY) {

        if (!normal) {
            offsetX -= distanceX;
            offsetX = Math.min((bigScale * mBitmap.getWidth() - getWidth()) / 2f, offsetX);
            offsetX = Math.max(-(bigScale * mBitmap.getWidth() - getWidth()) / 2f, offsetX);

            offsetY -= distanceY;
            offsetY = Math.min((bigScale * mBitmap.getHeight() - getHeight()) / 2f, offsetY);
            offsetY = Math.max(-(bigScale * mBitmap.getHeight() - getHeight()) / 2f, offsetY);
            invalidate();
        }

        return false;
    }

    // 长按
    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
        //int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY
        if (!normal) {

            mOverScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                    -(int) (bigScale * mBitmap.getWidth() - getWidth()) / 2,
                    (int) (bigScale * mBitmap.getWidth() - getWidth()) / 2,
                    -(int) (bigScale * mBitmap.getHeight() - getHeight()) / 2,
                    (int) (bigScale * mBitmap.getHeight() - getHeight()) / 2
            );

            postOnAnimation(this);
        }
        return false;
    }

    // 单击确认,实现双击后不再使用 onSingleTapUp
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    // 双击
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (normal) {
            getAnimator().start();
        } else {
            getAnimator().reverse();
        }
        normal = !normal;

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void run() {

        if (mOverScroller.computeScrollOffset()) {

            offsetX = mOverScroller.getCurrX();
            offsetY = mOverScroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }

    }

    ObjectAnimator mAnimator;

    private ObjectAnimator getAnimator() {
        if (mAnimator == null) {
            mAnimator = ObjectAnimator.ofFloat(this, "percent", 0, 1);
        }
        return mAnimator;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

    @Override
    public void bind(@NotNull ViewGroup viewGroup) {
        ExtenisonsKt.bind2ViewGroup(this, viewGroup);
    }

    @Override
    public void show() {

    }

}
