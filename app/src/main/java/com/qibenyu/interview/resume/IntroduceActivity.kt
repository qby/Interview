package com.qibenyu.interview.resume

import android.app.Activity
import android.os.Bundle
import com.qibenyu.interview.R
import kotlinx.android.synthetic.main.activity_introduce.*
import org.markdown4j.Markdown4jProcessor


class IntroduceActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduce)

        val input = resources.openRawResource(R.raw.resume)

        val html = Markdown4jProcessor().process(input)

        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)

    }
}