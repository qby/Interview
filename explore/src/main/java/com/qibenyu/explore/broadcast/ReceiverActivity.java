package com.qibenyu.explore.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ReceiverActivity extends Activity {

    private static final String TAG = "BroadcastReceiverActivi";

    private HomeReceiver mReceiver;


    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(TAG, "attachBaseContext: " + newBase);


        super.attachBaseContext(newBase);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        register();

    }

    private void register() {

        mReceiver = new HomeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("home_action");

        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);
    }

    private static class HomeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }

        @NonNull
        @Override
        public String toString() {
            return "HomeReceiver";
        }
    }
}
