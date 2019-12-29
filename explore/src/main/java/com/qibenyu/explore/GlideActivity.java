package com.qibenyu.explore;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class GlideActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView view = new ImageView(this);
        setContentView(view);

        Glide.with(this)
                .load("https://static.runoob.com/images/demo/demo1.jpg")
                .into(view);
    }
}
