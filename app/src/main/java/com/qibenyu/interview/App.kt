package com.qibenyu.interview

import android.app.Application
import android.content.Context
import android.util.Log


lateinit var sContext: Context

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        Log.d(TAG, "Context base = $base")

    }

    override fun onCreate() {
        super.onCreate()
        sContext = this
    }
}