package com.qibenyu.interview

import android.app.Activity
import com.qibenyu.algorithm.*
import com.qibenyu.architecture.jetpack.JetpackActivity
import com.qibenyu.explore.WindowManagerActivity
import com.qibenyu.ui.*
import com.qibenyu.ui.flow.FlowLayout


const val currentViewPagerItem = 1
val openItem =
    RemoveNthFromEnd::class.java

var uiList = arrayListOf<Class<out IShowable>>(
    Dashboard::class.java,
    MaterialEditText::class.java,
    PieChart::class.java,
    AvatarView::class.java,
    FlowLayout::class.java,
    MuahView::class.java
)

var patternMap = hashMapOf(
    "Flutter State Management" to R.raw.flutter_state
)

var algorithmMap = hashMapOf(
    "二分查找" to BinarySearch::class.java,
    "整数反转" to IntReverse::class.java,
    "字符串第一个不重复的字符" to FirstUniqueCharacterInString::class.java,
    "路径最短时间" to MinimumTimeVisitingAllPoints::class.java,
    "排序" to QuickSort::class.java,
    "两数求和" to TwoSum::class.java,
    "回文数" to PalindromeNumber::class.java,
    "回文链表" to ReverseLinkedList::class.java,
    "罗马数" to RomanNumber::class.java,
    "链表相交" to IntersectionTwoLinked::class.java,
    "删除数组中的重复项" to RemoveDuplicates::class.java,
    "删除单链表中的倒数第N个元素" to RemoveNthFromEnd::class.java
)


var architectureMap = hashMapOf<String, Class<out Activity>>(
    "Jetpack" to JetpackActivity::class.java
)

var exploreMap = hashMapOf<String, Class<out Activity>>(
//    "Window Manager" to WindowManagerActivity::class.java
)

