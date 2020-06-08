package com.qibenyu.explore;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.core.text.PrecomputedTextCompat;

import java.util.concurrent.Future;

public class TextActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_text);

        AppCompatTextView tv = findViewById(R.id.text);
        PrecomputedText.Params p = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            p = tv.getTextMetricsParams();
            PrecomputedText text = PrecomputedText.create("fjfjfjf", p);
            tv.setText(text);

        }
        Future<PrecomputedTextCompat> textFuture = PrecomputedTextCompat.getTextFuture("", tv.getTextMetricsParamsCompat(), null);

        tv.setTextFuture(textFuture);

        new AsyncLayoutInflater(this).inflate(R.layout.activity_text, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
                setContentView(view);

            }
        });
    }
}
