package com.qibenyu.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

// TODO: 2020/6/5 学习 BitmapRegionDecoder
public class BitView extends View implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private GestureDetector mGestureDetector;
    private BitmapFactory.Options mOptions;
    private Rect mRect;
    private Scroller mScroller;
    private int mImageWidht;
    private int mImageHeight;
    private int mViewWidth;
    private int mViewHight;
    private float mScale;
    private BitmapRegionDecoder mDecoder;
    private Bitmap mBitmap;

    public BitView(Context context) {
        super(context, null, 0);
    }

    public BitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public BitView(Context context, @Nullable AttributeSet attrs, int defStyleArrt) {
        super(context, attrs, defStyleArrt);

        //第一步：设置bitview的成员变量
        mRect = new Rect();
        //内存复用
        mOptions = new BitmapFactory.Options();
        //手势识别
        mGestureDetector = new GestureDetector(context, this);
        //滚动类
        mScroller = new Scroller(context);

        setOnTouchListener(this);
    }

    //第二步：设置图片，得到图片的信息
    public void setImage(InputStream inputStream) {
        //获取图片的宽和高（没有将图片加载到内存中）
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, mOptions);
        mImageWidht = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;

        //开启复用
        mOptions.inMutable = true;
        //设置格式
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;

        //
        mOptions.inJustDecodeBounds = false;

        //区域解码器
        try {
            mDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //第三步
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHight = getMeasuredHeight();

        //确定图片的加载区域
        mRect.top = 0;
        mRect.left = 0;
        mRect.right = mImageWidht;

        //根据图片的宽高，以及view的大小，来确定缩放因子
        mScale = mImageWidht / (float) mViewWidth;
        mRect.bottom = (int) (mViewHight / mScale);
    }

    //第四步
    private Matrix matrix;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDecoder == null) {
            return;
        }

        //内存复用(复用的bitmap必须跟即将得到的bitmap尺寸一样)
        mOptions.inBitmap = mBitmap;
        //指定区域解码
        mBitmap = mDecoder.decodeRegion(mRect, mOptions);
        if (matrix == null) {
            //得到矩阵进行缩放，得到view的大小
            matrix = new Matrix();
        }
        matrix.setScale(mScale, mScale);
        canvas.drawBitmap(mBitmap, matrix, null);
    }

    //第五步
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    //第六步
    @Override
    public boolean onDown(MotionEvent e) {
        //如果移动没有停止
        if (!mScroller.isFinished()) {
            mScroller.forceFinished(true);
        }
        return true;
    }

    //第七步
    //e1:开始事件，手机按下去，获取坐标
    //e2:获取当前坐标
    //xy:移动的距离
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //上下移动时,mRect需要改变现实的区域
        mRect.offset(0, (int) distanceY);

        //移动的时候需要处理到底顶部和底部的情况
        if (mRect.bottom > mImageHeight) {
            mRect.bottom = mImageHeight;
            mRect.top = mImageHeight - (int) (mImageHeight / mScale);
        }

        if (mRect.top < 0) {
            mRect.top = 0;
            mRect.bottom = (int) (mImageHeight / mScale);
        }
        invalidate();

        return false;
    }


    //第八步：处理惯性
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(0, mRect.top, 0, (int) -velocityY, 0, 0, 0, mImageHeight - (int) (mViewHight / mScale));
        return false;
    }

    //第九步：处理计算结果


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.isFinished()) {
            return;
        }

        //为true，说明滑动还没有结束
        if (mScroller.computeScrollOffset()) {
            mRect.top = mScroller.getCurrY();
            mRect.bottom = mRect.top + (int) (mImageHeight / mScale);
            invalidate();
        }
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    @Override
    public void onLongPress(MotionEvent e) {

    }


}