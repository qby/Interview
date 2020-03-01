package com.qibenyu.base.extension

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import android.text.TextUtils
import android.view.View
import java.util.*


fun Context.isRTL(): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
        return false
    }
    val applicationInfo: ApplicationInfo = this.applicationInfo
    val hasRtlSupport =
        applicationInfo.flags and ApplicationInfo.FLAG_SUPPORTS_RTL == ApplicationInfo.FLAG_SUPPORTS_RTL
    val isRtl =
        TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL
    return hasRtlSupport && isRtl
}
