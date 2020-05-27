package com.qibenyu.explore.handler

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class HandlerActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Thread {
            Looper.prepare()
            val handler = Handler()
            //闲时Handler
            Looper.myQueue().addIdleHandler {


                return@addIdleHandler false
            }
            //监听runnable执行时长
            Looper.myLooper()?.setMessageLogging {

            }
            Looper.loop()

        }.start()

    }

}

