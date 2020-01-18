package com.qibenyu.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

import com.qibenyu.base.ExtenisonsKt;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PagerView extends ViewGroup implements IShowable {
    private static final String TAG = "PagerView";

    private OverScroller mOverScroller;
    private ViewConfiguration mViewConfiguration;

    private VelocityTracker mVelocityTracker;

    private float mMaxFlingVelocity;
    private float mMinFlingVelocity;

    private int mCurrentPage = 0;
    private int mTouchSlop;

    public PagerView(Context context) {
        super(context);
        init(context);
    }

    public PagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mOverScroller = new OverScroller(context);
        mViewConfiguration = ViewConfiguration.get(context);
        mMaxFlingVelocity = mViewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlingVelocity = mViewConfiguration.getScaledMinimumFlingVelocity();
        mTouchSlop = mViewConfiguration.getScaledTouchSlop();
        mVelocityTracker = VelocityTracker.obtain();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int startWidth = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            view.layout(startWidth, 0, startWidth + getMeasuredWidth(), getMeasuredHeight());
            startWidth += getMeasuredWidth();
        }
    }

    private boolean scrolling = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mVelocityTracker.clear();
        }

        boolean result = false;
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                scrolling = false;
                downX = ev.getX();
                originalX = offsetX;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!scrolling) {
                    float dy = ev.getX() - downX;
                    if (Math.abs(dy) > mTouchSlop) {
                        scrolling = true;
                        result = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
        }
        return result;
    }

    private float downX;
    private float originalX;
    private float offsetX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mVelocityTracker.addMovement(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                originalX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = downX - event.getX() + originalX;

                float maxOffsetX = getWidth() * (getChildCount() - 1);
                if (offsetX > maxOffsetX) {
                    offsetX = maxOffsetX;
                } else if (offsetX < 0) {
                    offsetX = 0;
                }
                scrollTo((int) offsetX, 0);
                break;
            case MotionEvent.ACTION_UP:

                mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
                float vX = mVelocityTracker.getXVelocity();
                Log.d(TAG, "onTouchEvent: vx = " + vX + ", min = " + mMinFlingVelocity);
                if (Math.abs(vX) < mMinFlingVelocity) {
                    float upX = event.getX();
                    float dx = upX - downX;
                    if (Math.abs(dx) > getWidth() / 2f) {
                        if (dx > 0) {
                            mCurrentPage--;
                        } else {
                            mCurrentPage++;
                        }
                    }
                } else {
                    if (vX > 0) {
                        mCurrentPage--;
                    } else {
                        mCurrentPage++;
                    }
                }

                if (mCurrentPage > getChildCount() - 1) {
                    mCurrentPage = getChildCount() - 1;
                } else if (mCurrentPage < 0) {
                    mCurrentPage = 0;
                }

                int scrollDistance = mCurrentPage * getWidth() - getScrollX();
                mOverScroller.startScroll(getScrollX(), 0, scrollDistance, 0);
                postInvalidateOnAnimation();
                break;
        }
        return true;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    // 在draw方法中调用, postInvalidateOnAnimation 后自动调用
    @Override
    public void computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            scrollTo(mOverScroller.getCurrX(), mOverScroller.getCurrY());
            postInvalidateOnAnimation();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVelocityTracker.recycle();
    }

    @Override
    public void bind(@NotNull ViewGroup viewGroup) {
        ExtenisonsKt.bind2ViewGroup(this, viewGroup);
    }

    @Override
    public void show() {

        for (int i = 0; i < 4; i++) {
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            view.setBackgroundColor(COLORS[random.nextInt(COLORS.length)]);
            this.addView(view);
        }
    }

    private static final Random random = new Random();
    private static final int[] COLORS = {
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    };

}
