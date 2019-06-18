package com.qibenyu.interview

import android.os.Bundle
import android.support.v4.app.Fragment


class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}