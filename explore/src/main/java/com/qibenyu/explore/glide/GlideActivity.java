package com.qibenyu.explore.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class GlideActivity extends Activity {


    private ImageView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new ImageView(this);
        setContentView(view);

        //基本使用
        Glide.with(this)
                .load("https://static.runoob.com/images/demo/demo1.jpg")
                .into(view);
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Glide.with(this).clear(view);
    }
}
