package com.qibenyu.algorithm;

import android.view.View;
import android.view.ViewGroup;

public class ViewDepth {

    public int getDepth(View view) {

        if (!(view instanceof ViewGroup)) {
            return 0;
        }

        ViewGroup group = (ViewGroup) view;

        int maxDepth = 0;
        int childCount = group.getChildCount();
        for (int i = 0; i < childCount; i++) {

            int depth = getDepth(group.getChildAt(i)) + 1;

            maxDepth = Math.max(maxDepth, depth);
        }

        return maxDepth;
    }
}
