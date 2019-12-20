package com.qibenyu.explore.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EmptyService extends Service {

    private static final String TAG = "ServiceTest";

    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("my Handler Thread");
        thread.start();
        handler = new Handler(thread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopSelf();
                return false;
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.sendEmptyMessage(1);
        return super.onStartCommand(intent, flags, startId);
    }
}
