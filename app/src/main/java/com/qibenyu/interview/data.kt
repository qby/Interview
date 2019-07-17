package com.qibenyu.interview

import android.app.Activity
import com.qibenyu.algorithm.AlgorithmActivity
import com.qibenyu.architecture.jetpack.JetpackActivity
import com.qibenyu.framework.HandlerActivity
import com.qibenyu.ui.LoadingActivity


var uiMap = hashMapOf<String, Class<out Activity>>(
    "Loading" to LoadingActivity::class.java
)

var patternMap = hashMapOf<String, Class<out Activity>>()

var frameworkMap = hashMapOf<String, Class<out Activity>>(
    "Handler" to HandlerActivity::class.java
)

var algorithmMap = hashMapOf<String, Class<out Activity>>(
    "Binary Search" to AlgorithmActivity::class.java
)

var architectureMap = hashMapOf<String, Class<out Activity>>(
    "Jetpack" to JetpackActivity::class.java
)

var summaryMap = hashMapOf<String, Class<out Activity>>()

var exploreMap = hashMapOf<String, Class<out Activity>>()

