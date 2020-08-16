package com.qibenyu.architecture.jetpack

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.rjeschke.txtmark.Run
import java.util.concurrent.locks.ReentrantLock


class JetpackVM(app: Application) : AndroidViewModel(app) {

    private val handler = Handler()

//    val data: MutableLiveData<Int> = MutableLiveData()

//    val text: MutableLiveData<String> = MutableLiveData()

    var text : String = "王武"
    data class LiveMap(val mapName: MutableLiveData<String>, var square: Float)

    val map :MutableLiveData<LiveMap> = MutableLiveData()

    fun initViewBean() {
        handler.postDelayed({
            Log.d(TAG, "initViewBean: ")

//            map.value?.mapName?.value = "张三"
            text = "张三"
        }, 3000)

        text = "赵六"
    }

    companion object {
        private const val TAG = "JetpackVM"
    }

}