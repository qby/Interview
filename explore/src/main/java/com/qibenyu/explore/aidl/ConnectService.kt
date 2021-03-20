package com.qibenyu.explore.aidl

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.qibenyu.explore.IConnectCallback
import com.qibenyu.explore.IUserService

class ConnectService : Service() {

    companion object {
        private const val TAG = "ConnectService"
    }

    val handler = Handler()

    private val stub = object : IUserService.Stub() {
        override fun getUserName(): String {
            return "zhangsan"
        }

        override fun connectService(i: Int, calback: IConnectCallback) {

            Log.d(TAG, "server on call thread = ${Thread.currentThread().name}")
//            handler.postDelayed({
            Log.d(TAG, "server call thread = ${Thread.currentThread().name}")
            calback.onConnected(i, "success")
//            }, 1000)
        }

    }

    override fun onBind(intent: Intent?): IBinder {
        return stub
    }
}