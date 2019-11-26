package com.qibenyu.interview

import android.app.Activity
import com.qibenyu.algorithm.*
import com.qibenyu.architecture.jetpack.JetpackActivity
import com.qibenyu.ui.*
import com.qibenyu.ui.flow.FlowLayout


const val currentViewPagerItem = 1
val openItem = null//QuickSort::class.java

var uiList = arrayListOf<Class<out IShowable>>(
    Dashboard::class.java,
    MaterialEditText::class.java,
    PieChart::class.java,
    AvatarView::class.java,
    FlowLayout::class.java
)

var patternMap = hashMapOf(
    "Flutter State Management" to R.raw.flutter_state
)

var algorithmMap = arrayListOf(
    BinarySearch::class.java,
    IntReverse::class.java,
    FirstUniqueCharacterInString::class.java,
    MinimumTimeVisitingAllPoints::class.java,
    QuickSort::class.java
)


var architectureMap = hashMapOf<String, Class<out Activity>>(
    "Jetpack" to JetpackActivity::class.java
)

var exploreMap = hashMapOf<String, Class<out Activity>>()

