package com.qibenyu.interview

import android.app.Application
import android.content.Context


lateinit var sContext: Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        sContext = this
    }
}