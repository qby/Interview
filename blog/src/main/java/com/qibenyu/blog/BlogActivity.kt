package com.qibenyu.blog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_blog.*
import org.markdown4j.Markdown4jProcessor


class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)

        val blogId = intent.getIntExtra("blogId", R.raw.flutter_state)

        val input = resources.openRawResource(blogId)

        val html = Markdown4jProcessor().process(input)

        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)

    }
}