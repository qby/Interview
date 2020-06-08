package com.qibenyu.algorithm;

import android.view.View;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

public class ViewCommonAncestor {

    public View getCommonAncestor(View v1, View v2) {


        HashSet<View> set = new HashSet<>();

        while(v1 != null) {
            set.add(v1);
            v1 = (View) v1.getParent();
        }

        while(v2 != null) {
            if (set.contains(v2)) {
                return v2;
            }
            v2 = (View) v2.getParent();
        }

        return null;

    }
}
