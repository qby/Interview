package com.qibenyu.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ui_show.*
import java.lang.Exception


class UIShowActivity : AppCompatActivity() {

    companion object {
        const val TAG = "UIShowActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ui_show)

        val viewName = intent.getStringExtra("UI_SHOW")

        title = viewName

        showView(viewName)
    }

    private fun showView(viewName: String) {

        try {
            val clazz = Class.forName(viewName)
            val constructor = clazz.getConstructor(Context::class.java)
            val instance = constructor.newInstance(this)

            val showable = instance as IShowable

            showable.bind(container)

            showable.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}