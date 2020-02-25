package com.baronqi.pattern.decorator;

import android.util.Log;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        Log.d(DecoratorActivity.TAG, "Shape: Rectangle");
    }
}