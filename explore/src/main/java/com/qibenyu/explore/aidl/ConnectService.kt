package com.qibenyu.explore.aidl

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.qibenyu.explore.IConnectCallback
import com.qibenyu.explore.IUserService
import java.lang.ref.Reference
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.*

class ConnectService : Service() {

    companion object {
        private const val TAG = "ConnectService"
    }

    val handler = Handler()

    val list = LinkedList<IConnectCallback>()
    val referenceQueue = ReferenceQueue<IConnectCallback>()

    private val stub = object : IUserService.Stub() {
        override fun getUserName(): String {
            return "zhangsan"
        }

        override fun connectService(i: Int, calback: IConnectCallback) {

//            list.add(calback)
            val weakReference = WeakReference(calback, referenceQueue)

            weakReference.get()?.asBinder()?.linkToDeath({
                Log.e(TAG, "connectService: $i")
            }, 0)
            Log.d(TAG, "server on call thread = ${Thread.currentThread().name} ," +
                    " stub count = ${list.size}" +
                    " i = $i")
//            handler.postDelayed({
                Log.d(TAG, "server call thread = ${Thread.currentThread().name}")
            weakReference.get()?.onConnected(i, "success")
//            }, 1000)

            if (i >= 10000) {
                loopQueue()
            }
        }

    }

    private fun loopQueue() {
        Log.d(TAG, "loopQueue() called")
        var ref : Reference<IConnectCallback>?

        do {
            ref =
                referenceQueue.poll() as Reference<IConnectCallback>?// as Reference<IConnectCallback>
            if (ref != null) {
                Log.d(TAG, "loopQueue: 被回收")
            }
        } while (ref != null)
    }

    override fun onBind(intent: Intent?): IBinder {
        return stub
    }
}