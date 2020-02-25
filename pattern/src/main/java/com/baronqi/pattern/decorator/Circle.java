package com.baronqi.pattern.decorator;

import android.util.Log;

public class Circle implements Shape {

    @Override
    public void draw() {
        Log.d(DecoratorActivity.TAG, "Shape: Circle");
    }
}