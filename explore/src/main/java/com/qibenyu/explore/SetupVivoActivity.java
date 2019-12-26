package com.qibenyu.explore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class SetupVivoActivity extends Activity {
    private static final String TAG = "SetupVivoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        intent.setAction("android.settings.APPLICATION_SETTINGS");
//        intent.setClassName("com.android.settings", "com.android.settings.Settings$SecurityAndPrivacySettingsActivity");
//        startActivity(intent);

//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        intent.setAction("android.intent.action.MAIN");
//        intent.setClassName("com.android.settings", "com.vivo.settings.VivoSubSettings");
//        startActivity(intent);

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.setAction("android.settings.APPLICATION_SETTINGS");
        intent.setClassName("com.android.settings","com.android.settings.Settings$GeneralSettingsActivity");
//        intent.setAction("android.settings.SETTINGS.SUB_SETTINGS");
//        intent.putExtra("extra", "fragment:com.vivo.settings.DesktopUsageRightsFragment");

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
}
