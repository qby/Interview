package com.qibenyu.explore.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.qibenyu.explore.R;

public class NotificationService extends Service {

    private static final String TAG = "ServiceTest";

    private static final int NOTIFICATION_ID = 100;


    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.createNotificationChannel(new NotificationChannel("default_channelId",
                    "default_channelId", NotificationManager.IMPORTANCE_LOW));
            startForeground(NOTIFICATION_ID, new Notification.Builder(this,
                    "default_channelId")
                    .setSmallIcon(Icon.createWithResource(this, R.drawable.ic_launcher)).build());
        }


        handler = new Handler();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");

        try {
            Intent targetIntent = intent.getParcelableExtra(ServiceCompat.KEY_INTENT);
            startService(targetIntent);
//            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
//                @Override
//                public boolean queueIdle() {
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            stopSelf();
//                        }
//                    }, 3 * 60 * 1000);
//                    return false;
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
