package com.qibenyu.ui.flow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.strictmode.Violation;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.qibenyu.base.ExtenisonsKt;
import com.qibenyu.ui.ColoredTextView;
import com.qibenyu.ui.IShowable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FlowLayout extends ViewGroup implements IShowable {

    private List<String> mDataList;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    List<Rect> rectList = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int lineHeight = 0;

        int widthUsed = 0;
        int heightUsed = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);

            if (widthMode != MeasureSpec.UNSPECIFIED &&
                    widthUsed + child.getMeasuredWidth() > width) {
                heightUsed += lineHeight;
                widthUsed = 0;
                lineHeight = 0;
//                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect rect;
            if (i >= rectList.size()) {
                rect = new Rect();
                rectList.add(rect);
            } else {
                rect = rectList.get(i);
            }

            rect.set(widthUsed, heightUsed,
                    widthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());

            widthUsed += child.getMeasuredWidth();
            lineHeight = Math.max(child.getMeasuredHeight(), lineHeight);

        }

        int realWidth = widthUsed;
        int realHeight = heightUsed + lineHeight;
        setMeasuredDimension(width, realHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            Rect rect = rectList.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public void bind(@NotNull ViewGroup viewGroup) {
        ExtenisonsKt.bind2ViewGroup(this, viewGroup);

        View view = new View(getContext());
        view.setLayoutParams(new LayoutParams(200,200));
        view.setBackgroundColor(Color.BLACK);
        viewGroup.addView(view);

    }

    @Override
    public void show() {
        for (int i = 0; i < 30; i++) {
            addView(generateChild());
        }
    }

    private static final Random random = new Random();

    public static List<String> provinces = Arrays.asList("北京市",
            "天津市",
            "上海市",
            "重庆市",
            "河北省",
            "山西省",
            "辽宁省",
            "吉林省",
            "黑龙江省",
            "江苏省",
            "浙江省",
            "安徽省",
            "福建省",
            "江西省",
            "山东省",
            "河南省",
            "湖北省",
            "湖南省",
            "广东省",
            "海南省",
            "四川省",
            "贵州省",
            "云南省",
            "陕西省",
            "甘肃省",
            "青海省",
            "台湾省",
            "内蒙古自治区",
            "广西壮族自治区",
            "西藏自治区",
            "宁夏回族自治区",
            "新疆维吾尔自治区",
            "香港特别行政区",
            "澳门特别行政区");

    private ColoredTextView generateChild() {
        ColoredTextView view = new ColoredTextView(getContext());
        view.setLayoutParams(new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        view.setText(provinces.get(random.nextInt(20)));

        return view;
    }
}
