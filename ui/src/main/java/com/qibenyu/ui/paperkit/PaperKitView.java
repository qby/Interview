package com.qibenyu.ui.paperkit;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
public class PaperKitView extends ViewGroup {

    private List<View> mData;

    private Adapter mAdapter;

    public PaperKitView(Context context) {
        super(context);
    }

    public PaperKitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
        // TODO: 2020-01-18 notify
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mAdapter == null) {
            return;
        }

        defaultOnMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension();

    }

    private void defaultOnMeasure(int widthSpec, int heightSpec) {
        final int width = RecyclerView.LayoutManager.chooseSize(widthSpec,
                getPaddingLeft() + getPaddingRight(),
                ViewCompat.getMinimumWidth(this));
        final int height = RecyclerView.LayoutManager.chooseSize(heightSpec,
                getPaddingTop() + getPaddingBottom(),
                ViewCompat.getMinimumHeight(this));

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public static final class Adapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
