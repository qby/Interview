package com.qibenyu.explore.glide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qibenyu.explore.handler.HandlerActivity;


public class GlideActivity extends Activity {

    private static final String TAG = "GlideActivity";

    private ImageView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new ImageView(this);
        setContentView(view);
        Log.d(TAG, "onCreate: ");

        view.setOnClickListener(v -> getWindow().getDecorView().postDelayed(() ->
                startActivity(new Intent(GlideActivity.this, GlideActivity.class)),2000));

        //基本使用
        Glide.with(this)
                .load("https://static.runoob.com/images/demo/demo1.jpg")
                .into(view);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
