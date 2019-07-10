package com.qibenyu.interview

import android.app.Activity
import com.qibenyu.algorithm.AlgorithmActivity
import com.qibenyu.ui.LoadingActivity


var uiMap = hashMapOf<String, Class<out Activity>>(
    "Loading" to LoadingActivity::class.java
)

var patternMap = hashMapOf<String, Class<out Activity>>()

var frameworkMap = hashMapOf<String, Class<out Activity>>()

var algorithmMap = hashMapOf<String, Class<out Activity>>(
    "Binary Search" to AlgorithmActivity::class.java
)

var architectureMap = hashMapOf<String, Class<out Activity>>()

var summaryMap = hashMapOf<String, Class<out Activity>>()

var exploreMap = hashMapOf<String, Class<out Activity>>()

