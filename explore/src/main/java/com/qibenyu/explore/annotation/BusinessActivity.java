package com.qibenyu.explore.annotation;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.qibenyu.explore.R;

public class BusinessActivity extends Activity {

    private static final String TAG = "BusinessActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        findViewById(R.id.text).setOnClickListener((view) -> {
            openBusiness();
        });

    }

    public void openBusiness() {

        Log.d(TAG, "openBusiness: ");

    }


}
