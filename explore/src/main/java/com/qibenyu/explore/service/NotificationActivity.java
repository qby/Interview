package com.qibenyu.explore.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotificationActivity extends Activity {

    private static final String TAG = "ServiceTest";
    Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {

            Log.d(TAG, "handleMessage() called with: msg = [" + msg.what + "]");
            switch (msg.what) {

                case 1:
//                    int timemill = 0;
//                    for (int i = 2; i < 4; i++) {
//                        handler.sendEmptyMessageDelayed(i, timemill);
//                        timemill += 1000 * 66;
//                    }
//                    break;
//                case 2:
                    Intent intent = new Intent(NotificationActivity.this, EmptyService.class);
                    ServiceCompat.start(NotificationActivity.this, intent);
//                    break;
//                case 3:
                    Intent intent2 = new Intent(NotificationActivity.this, Empty2Service.class);
                    ServiceCompat.start(NotificationActivity.this, intent2);
                    break;

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            handler.sendEmptyMessageDelayed(1, 65 * 1000);

        }
    }
}
