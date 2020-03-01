package com.qibenyu.base

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


object SP {
    val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(sContext)

    var HOST by SPDelegates.string("")
}

/**
 * SP类的代理类，使用属性代理
 */
private object SPDelegates {

    fun int(defaultValue: Int = 0) = object : ReadWriteProperty<SP, Int> {
        override fun getValue(thisRef: SP, property: KProperty<*>): Int {
            return thisRef.preference.getInt(property.name, defaultValue)
        }

        override fun setValue(thisRef: SP, property: KProperty<*>, value: Int) {
            thisRef.preference.edit().putInt(property.name, value).apply()
        }

    }

    fun long(defaultValue: Long = 0L) = object : ReadWriteProperty<SP, Long> {

        override fun getValue(thisRef: SP, property: KProperty<*>): Long {
            return thisRef.preference.getLong(property.name, defaultValue)
        }

        override fun setValue(thisRef: SP, property: KProperty<*>, value: Long) {
            thisRef.preference.edit().putLong(property.name, value).apply()
        }
    }

    fun boolean(defaultValue: Boolean = false) =
        object : ReadWriteProperty<SP, Boolean> {
            override fun getValue(thisRef: SP, property: KProperty<*>): Boolean {
                return thisRef.preference.getBoolean(property.name, defaultValue)
            }

            override fun setValue(thisRef: SP, property: KProperty<*>, value: Boolean) {
                thisRef.preference.edit().putBoolean(property.name, value).apply()
            }
        }

    fun float(defaultValue: Float = 0.0f) =
        object : ReadWriteProperty<SP, Float> {
            override fun getValue(thisRef: SP, property: KProperty<*>): Float {
                return thisRef.preference.getFloat(property.name, defaultValue)
            }

            override fun setValue(thisRef: SP, property: KProperty<*>, value: Float) {
                thisRef.preference.edit().putFloat(property.name, value).apply()
            }
        }

    fun string(defaultValue: String) = object : ReadWriteProperty<SP, String> {
        override fun getValue(thisRef: SP, property: KProperty<*>): String {
            return thisRef.preference.getString(property.name, defaultValue) ?: ""
        }

        override fun setValue(thisRef: SP, property: KProperty<*>, value: String) {
            thisRef.preference.edit().putString(property.name, value).apply()
        }
    }
}