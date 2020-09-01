package com.baronqi.kotlin

import android.util.Log
import com.qibenyu.base.extension.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {


    GlobalScope.launch(Dispatchers.Default) {

        io1()
        ui1()


    }

}

suspend fun io1() {
    withContext(Dispatchers.IO) {
//        Log.d(TAG, )
        println("io1: ${Thread.currentThread().name}")
    }
}

fun ui1() {
//    Log.d(TAG, "ui1: ${Thread.currentThread().name}")
    println("ui1: ${Thread.currentThread().name}")
}

