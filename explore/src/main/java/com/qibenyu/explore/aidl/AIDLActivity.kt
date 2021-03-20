package com.qibenyu.explore.aidl

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.TextView
import com.qibenyu.explore.IConnectCallback
import com.qibenyu.explore.IUserService

class AIDLActivity : Activity() {

    lateinit var userService: IUserService

    companion object {
        private const val TAG = "ConnectService"

    }

    val handler = Handler()

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder) {
            userService = IUserService.Stub.asInterface(service)
            onServiceConnect()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    private fun onServiceConnect() {
//        for (index in 1..100000) {
//            handler.postDelayed({
                aidlCall(0)
//            }, 1000)
//        }

    }

    private fun aidlCall(i: Int) {
        Log.d(TAG, "client: call thread = ${Thread.currentThread().name}")
        userService.connectService(i, object : IConnectCallback.Stub() {
            override fun onConnected(code: Int, message: String?) {
                Log.d(TAG, "client on call thread = ${Thread.currentThread().name}")
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TextView(this))
        Log.d(TAG, "onCreate: ")
        handler.postDelayed({
            bindService(
                Intent(this, ConnectService::class.java),
                connection,
                Service.BIND_AUTO_CREATE
            )
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}