package com.qibenyu.explore.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * 兼容8.0及以上的系统后台应用启动服务
 */
public class ServiceCompat {
    public static final String KEY_INTENT = "cml_intent";
    private static final String TAG = "ServiceTest";

    public static void start(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            context.startService(intent);
        } else {
            try {
                context.startService(intent);
            } catch (Exception e) {
                e.printStackTrace();
                //8.0及以上系统后台应用启动服异常后,先启动一个前台服务然后再启动目标服务
                Log.d(TAG, "start: exception = " + e.getMessage());
                if (e instanceof IllegalStateException) {
                    Intent foregroundIntent = new Intent(context,
                            NotificationService.class);
                    foregroundIntent.putExtra(KEY_INTENT, intent);
                    try {
                        context.startForegroundService(foregroundIntent);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
