package com.qibenyu.architecture.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qibenyu.architecture.R
import com.qibenyu.architecture.databinding.JetpackBinding


class JetpackActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Jetpack"
    }

    private lateinit var viewModel : JetpackVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : JetpackBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_jetpack)

        viewModel = ViewModelProvider(this).get(JetpackVM::class.java)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.initViewBean()

    }
}