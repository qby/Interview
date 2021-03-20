package com.qibenyu.explore.handler

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import com.qibenyu.explore.glide.GlideActivity
import kotlin.math.log

class HandlerActivity : Activity() {


    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {

            Log.d(TAG, "handleMessage: ")
        }
    }

    companion object{
        private const val TAG = "HandlerActivity"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.d("Baron", "myapplication = ${getMyApplication()}")


    }

    private fun getMyApplication(): Application {

        val clazz = Class.forName("android.app.ActivityThread")

        val method = clazz.getDeclaredMethod("currentApplication")

        return method.invoke(null) as Application

    }

}

