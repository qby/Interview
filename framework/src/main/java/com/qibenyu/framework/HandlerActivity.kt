package com.qibenyu.framework

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_handler.*

class HandlerActivity : AppCompatActivity() {

    companion object {
        const val TAG = "HandlerActivity"
    }

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        Log.d(TAG,"handler looper = ${mHandler.looper}")


    }

}
