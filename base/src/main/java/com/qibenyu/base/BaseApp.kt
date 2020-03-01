package com.qibenyu.base

import android.app.Application
import android.content.Context


lateinit var sContext: Context

open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        sContext = this
    }

}