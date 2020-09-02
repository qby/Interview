package com.qibenyu.interview

import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.qibenyu.base.BaseApp
import com.qibenyu.explore.broadcast.HackAMS

class App : BaseApp() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.d(TAG, "Context base = $base")
    }

    override fun onCreate() {
        super.onCreate()

        HackAMS.hookAMSAfter26()

    }
}