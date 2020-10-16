package com.qibenyu.interview

import android.app.Activity
import com.baronqi.pattern.decorator.DecoratorActivity
import com.qibenyu.algorithm.*
import com.qibenyu.architecture.jetpack.JetpackActivity
import com.qibenyu.explore.broadcast.ReceiverActivity
import com.qibenyu.explore.glide.GlideActivity
import com.qibenyu.explore.handler.HandlerActivity
import com.qibenyu.explore.leak.LeakCanaryActivity
import com.qibenyu.explore.litho.LithoActivity
import com.qibenyu.explore.tiktok.TikTokActivity
import com.qibenyu.explore.windowmanager.WindowManagerActivity
import com.qibenyu.ui.*
import com.qibenyu.ui.flow.FlowLayout
import com.qibenyu.ui.paperkit.PaperKitView


/**
 * 0 : UI
 * 1 : Algorithm
 * 2 : Blog
 * 3 : Architecture
 * 4 : Explore
 * 5 : Pattern
 */
const val currentViewPagerItem = 0
val openItem = ChargingView::class.java

//    DecoratorActivity::class.java
//    PaperKitView::class.java
//    MultiTouchView::class.java
//    ScalableImageView::class.java
//    PagerView::class.java

var uiList = arrayListOf<Class<out IShowable>>(
    ChargingView::class.java,
    PagerView::class.java,
    PaperKitView::class.java,
    Dashboard::class.java,
    MaterialEditText::class.java,
    PieChart::class.java,
    AvatarView::class.java,
    FlowLayout::class.java,
    MuahView::class.java,
    StepView::class.java,
    ScalableImageView::class.java,
    MultiTouchView::class.java
)

var blogMap = hashMapOf(
    "OKHttp" to R.raw.okhttp,
    "Flutter State Management" to R.raw.flutter_state
)

var algorithmMap = linkedMapOf(
    "二分查找" to BinarySearch::class.java,
    "整数反转" to IntReverse::class.java,
    "字符串第一个不重复的字符" to FirstUniqueCharacterInString::class.java,
    "路径最短时间" to MinimumTimeVisitingAllPoints::class.java,
    "排序" to Sorts::class.java,
    "两数求和" to TwoSum::class.java,
    "回文数" to PalindromeNumber::class.java,
    "回文链表" to ReverseLinkedList::class.java,
    "罗马数" to RomanNumber::class.java,
    "链表相交" to IntersectionTwoLinked::class.java,
    "删除数组中的重复项" to RemoveDuplicates::class.java,
    "删除单链表中的倒数第N个元素" to RemoveNthFromEnd::class.java,
    "HashMap" to HashMap::class.java,
    "链表相交" to IntersectionTwoLinked::class.java,
    "优先级队列" to MyPriorityQueue::class.java,
    "二叉树的序列化与反序列化" to TreeSerialized::class.java,
    "镜像二叉树" to MirrorTree::class.java,
    "链表排序" to LinkedSorted::class.java
)


var architectureMap = hashMapOf<String, Class<out Activity>>(
    "Jetpack" to JetpackActivity::class.java
)

var exploreMap = hashMapOf(
    "Window Manager" to WindowManagerActivity::class.java,
    "Glide" to GlideActivity::class.java,
    "Handler" to HandlerActivity::class.java,
    "LeakCanary" to LeakCanaryActivity::class.java,
    "BroadcastReceiver Hook" to ReceiverActivity::class.java,
    "Litho" to LithoActivity::class.java,
    "BroadcastReceiver Hook" to ReceiverActivity::class.java,
    "TikTok" to TikTokActivity::class.java

)

var patternMap = hashMapOf<String, Class<out Activity>>(
    "Decorator" to DecoratorActivity::class.java
)

