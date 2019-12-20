package com.qibenyu.explore;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.util.Printer;

import androidx.annotation.Nullable;

public class SetupVivoActivity extends Activity {
    private static final String TAG = "SetupVivoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent();
//        intent.setClassName("com.android.settings", ".Settings$DesktopUsageRightActivity");
//        startActivity(intent);

        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.d(TAG, "queueIdle() called");
                return false;
            }
        });

        Looper.myLooper().setMessageLogging(new Printer() {
            @Override
            public void println(String s) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
}
