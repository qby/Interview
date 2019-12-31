package com.qibenyu.explore

import android.app.Activity
import android.os.Bundle
import android.os.Handler

class HandlerActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Thread {
            val handler = Handler()
        }.start()

    }

}

