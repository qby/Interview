package com.qibenyu.ui.paperkit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.OverScroller;

import androidx.core.view.ViewCompat;

import com.qibenyu.base.ExtenisonsKt;
import com.qibenyu.ui.ColoredTextView;
import com.qibenyu.ui.IShowable;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * PaperKite Android Technical Challenge
 * Build a single screen application that allows the user to choose 3 different preferences using carousels(轮播) (coverflow).
 * Key Features:
 * •	Swipe through three vertically stacked carousels with paging enabled on each of them.
 * •    滑动通过三个垂直堆叠的轮播，并在每个轮播上启用分页。
 * •	Saolling should be continuous but sticky, so that there's gravity pulling the centre view to the centre.
 * •    滑行应连续但发粘，以便重力将中心视图拉到中心。
 * •    The user can visually see the 2 items before and after the selected item.
 * •    用户可以直观地看到所选项目之前和之后的2个项目。
 * •    The opacity and scale of the items to the left & right of the selected item gradually decrease in a Iin63「m3nri6r.
 * •    所选项目左右两侧的项目的不透明度和比例会逐渐减小。
 * •	The carousels should have smooth transitions.
 * •    转盘应具有平滑的过渡。
 * •	Built in a scalable and reusable way.
 * •    以可扩展和可重用的方式构建。
 * •Nice To Haves:
 * •	The carousels can support custom views.
 * •    轮播可以支持自定义视图。
 * •	Circular carousel
 * •    圆形转盘循环轮播
 * •Notes:
 * •	The app should have backwards compatibility to at least AP119.
 * •	You can use any assets and colours.
 * •	Keep notes where you think it is relevant about your implementation and how it works.
 */
public class PaperKitView extends ViewGroup implements IShowable {
    private static final String TAG = "PaperKitView";

    public static final float MIN_ALPHA = 0.8f;
    public static final float MIN_SCALE = 0.8f;

    private List<View> mData;

    private Adapter mAdapter;

    private VelocityTracker mVelocityTracker;

    private OverScroller mOverScroller;

    private int mTouchSlop;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;

    public PaperKitView(Context context) {
        super(context);
        init();
    }

    public PaperKitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mVelocityTracker = VelocityTracker.obtain();
        mOverScroller = new OverScroller(getContext());
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinFlingVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = configuration.getScaledMaximumFlingVelocity();

    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();

//        int left = l + getPaddingLeft();
//        int right = r - l - getPaddingRight();
//        int top = t + getPaddingTop();
//        int bottom = b - t - getPaddingBottom();

        float start = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);

            if (view.getVisibility() == GONE) {
                continue;
            }

            if (start == 0) {
                start = getWidth() / 2f - view.getWidth() / 2f;
            }
            float top = getHeight() / 2f - view.getHeight() / 2f;
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            view.layout(
                    (int) start + params.leftMargin,
                    (int) top + params.topMargin,
                    (int) start + view.getMeasuredWidth() + params.rightMargin,
                    (int) top + view.getMeasuredHeight() + params.bottomMargin);

//            Log.d(TAG, "onLayout: left = " + left);
            start += (view.getMeasuredWidth() + params.rightMargin + params.leftMargin);

        }
        if (changed) {

            computeViewAlphaAndScale();
        }
    }

    private float downX, downY;

    private boolean mScrolling = false;

    private int mScreenCenterX = 0;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenCenterX = w / 2;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

//        canvas.save();
//        canvas.translate(getMeasuredWidth() * getChildCount(), 0);
//        super.dispatchDraw(canvas);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(-getMeasuredWidth() * getChildCount(), 0);
//        super.dispatchDraw(canvas);
//        canvas.restore();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false;

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                originalX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScrolling) {
                    mScrolling = true;
                    result = true;
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return result;
    }

    private float originalX, originalY;

    private int mCurrentTag = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mVelocityTracker.addMovement(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                originalX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = downX - event.getX() + originalX;
                computeViewAlphaAndScale();
                scrollTo((int) dx, 0);
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                float vX = mVelocityTracker.getXVelocity();
//                if (Math.abs(vX) > mMinFlingVelocity) {
//
//                } else {

                float centerViewDistance = Float.MAX_VALUE;
//                    float dCenterX = Float.MAX_VALUE;
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    float viewScrollX = child.getX() + child.getMeasuredWidth() / 2f - getScrollX();
                    float dCenterX = mScreenCenterX - viewScrollX;
                    Log.d(TAG, "onTouchEvent: dCenterX = " + dCenterX);
                    centerViewDistance = Math.min(Math.abs(dCenterX) - child.getWidth() / 2f, centerViewDistance);
                }

                Log.d(TAG, "onTouchEvent: centerx = " + centerViewDistance);
                mOverScroller.startScroll(getScrollX(), 0, (int) centerViewDistance, 0);
//                }
                break;
        }
        return true;
    }

    private void computeViewAlphaAndScale() {

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            float viewScrollX = view.getX() + view.getMeasuredWidth() / 2f - getScrollX();
            float dCenterX = Math.abs(mScreenCenterX - viewScrollX) - view.getWidth() / 2f;
            view.setAlpha(1 - (dCenterX / mScreenCenterX));
            float scale = Math.max(1 - (dCenterX / mScreenCenterX), MIN_SCALE);
            view.setScaleX(scale);
            view.setScaleY(scale);
        }

    }

    @Override
    public void computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            scrollTo(mOverScroller.getCurrX(), mOverScroller.getCurrY());
            postInvalidateOnAnimation();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    public void bind(@NotNull ViewGroup viewGroup) {
        ExtenisonsKt.bind2ViewGroup(this, viewGroup);
    }

    @Override
    public void show() {

        for (int i = 0; i < 20; i++) {
            ColoredTextView view = new ColoredTextView(getContext());
            LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            p.rightMargin = (int) ExtenisonsKt.dp(10);
            p.leftMargin = (int) ExtenisonsKt.dp(10);
            view.setLayoutParams(p);
//            view.setBackgroundColor(COLORS[random.nextInt(COLORS.length)]);
            this.addView(view);
        }

        Log.d(TAG, "show: measure = " + getMeasuredWidth());
    }

    public static final class LayoutParams extends ViewGroup.MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    public static final class Adapter<T> extends BaseAdapter {

        private List<T> mData;

        public Adapter(List<T> data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public T getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ColoredTextView tv = new ColoredTextView(parent.getContext());
            return tv;
        }

    }
}
