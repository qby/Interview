package com.qibenyu.explore

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.os.Environment
import androidx.core.content.ContextCompat
import java.io.File


class LeakCanaryActivity : Activity() {


    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            dump()
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        }
    }

    private fun dump() {

        val downloadsDirectory = Environment.getExternalStoragePublicDirectory("Download")
        val f =  File(downloadsDirectory, "leakcanary-$packageName")

        Debug.dumpHprofData(f.absolutePath)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        dump()

    }

}