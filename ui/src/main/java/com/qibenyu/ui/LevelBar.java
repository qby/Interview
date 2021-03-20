package com.qibenyu.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.view.menu.ShowableListMenu;

import com.qibenyu.base.extension.ExtenisonsKt;

import org.jetbrains.annotations.NotNull;

/**
 * Author cocwong
 * Date 2018/1/4 0004 10:28
 * E-mail cocwong@163.com
 */

public class LevelBar extends View implements IShowable {
    private int levelTextSize;
    private int levelTextChooseColor;
    private int levelTextUnChooseColor;
    private int progressStartColor;
    private int progressEndColor;
    private int progressBgColor;
    private int progressHeight;

    private int textHeight, totalWidth;

    private int currentLevel = 10;

    private String[] levels;//上方text数组

    private Paint mPaint;

    private int progress = 10;//0-100

    public LevelBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        getValues(attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(levelTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            textHeight = (int) (mPaint.descent() - mPaint.ascent());
            height = getPaddingBottom() + getPaddingTop() + dpToPx(10) + textHeight + progressHeight;
        }
        setMeasuredDimension(width, height);
        totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getPaddingLeft(), getPaddingTop());
        drawText(canvas);//draw上方文字
        drawBgProgress(canvas);//draw背景
        drawProgress(canvas);//draw进度
        canvas.restore();
    }

    private void drawProgress(Canvas canvas) {
        int startX = progressHeight / 2, stopX = (int) (totalWidth * (progress / 100f) - progressHeight / 2);
        if (currentLevel < 1 || startX >= stopX) return;
        Shader shader = new LinearGradient(0, 0, totalWidth, 0, progressStartColor, progressEndColor, Shader.TileMode.REPEAT);
        mPaint.setShader(shader);
        canvas.drawLine(startX, 0, stopX, 0, mPaint);
        mPaint.setShader(null);
    }

    private void drawBgProgress(Canvas canvas) {
        float lineY = dpToPx(10) + textHeight;
        canvas.translate(0, lineY);
        mPaint.setColor(progressBgColor);
        mPaint.setStrokeWidth(progressHeight);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(progressHeight / 2, 0, totalWidth - progressHeight / 2, 0, mPaint);
    }

    private void drawText(Canvas canvas) {
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        if (levels == null) throw new IllegalArgumentException("等级不能为null");
        for (int i = 0; i < levels.length; i++) {
            int textLength = (int) mPaint.measureText(levels[i]);
            if (currentLevel == (i + 1))
                mPaint.setColor(levelTextChooseColor);
            else
                mPaint.setColor(levelTextUnChooseColor);
            float baseLine = textHeight / 2 + (Math.abs(mPaint.ascent()) - mPaint.descent()) / 2;
            canvas.drawText(levels[i], totalWidth / levels.length * (i + 1) - textLength, baseLine, mPaint);
        }
    }

    /**
     * 获取自定义属性
     *
     * @param attrs attrs
     */
    private void getValues(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.LevelBar);
        levelTextSize = array.getDimensionPixelSize(R.styleable.LevelBar_levelTextSize, dpToPx(15));
        levelTextChooseColor = array.getColor(R.styleable.LevelBar_levelTextChooseColor, 0xFFEE3B3B);
        levelTextUnChooseColor = array.getColor(R.styleable.LevelBar_levelTextUnChooseColor, 0xFF8B8989);
        progressStartColor = array.getColor(R.styleable.LevelBar_progressStartColor, 0xFFFFDAB9);
        progressEndColor = array.getColor(R.styleable.LevelBar_progressEndColor, 0xFFFF8C00);
        progressBgColor = array.getColor(R.styleable.LevelBar_progressBgColor, 0xFFD1D1D1);
        progressHeight = array.getDimensionPixelSize(R.styleable.LevelBar_progressHeight, dpToPx(10));
        array.recycle();
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public void setLevels(String[] levels) {
        this.levels = levels;
    }

    public void setLevel(int level) {
        if (currentLevel == level) return;
        int levelSubtract = Math.abs(level - currentLevel);
        currentLevel = level;
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", progress, 100 * level / levels.length);
        animator.setDuration(300 * levelSubtract);
        animator.setInterpolator(new LinearInterpolator());
        animator.setAutoCancel(true);
        animator.start();
    }

    private int getProgress() {
        return progress;
    }

    private void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    public void bind(@NotNull ViewGroup viewGroup) {
        ExtenisonsKt.bind2ViewGroup(this, viewGroup);
    }

    @Override
    public void show() {
        setLevels(new String[]{
                "1","2","3", "4"
        });
        setLevel(2);

        setProgress(2);
    }
}