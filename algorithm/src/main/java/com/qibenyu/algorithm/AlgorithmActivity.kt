package com.qibenyu.algorithm

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.acitivity_algorithm.*
import java.lang.Exception


class AlgorithmActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_algorithm)

        val algorithmName = intent.getStringExtra("ALGORITHM")

        val algorithm = getAlgorithm(algorithmName)

        title = algorithm?.problem()

        problem.text = algorithm?.problem()
        condition.text = algorithm?.condition()
        answer.text = algorithm?.answer()

        showThoughtTitle(algorithm)
    }

    private fun showThoughtTitle(algorithm: IAlgorithm?) {
        val hasThought = !TextUtils.isEmpty(algorithm?.thought())
        if (hasThought) {
            thoughtTitle.visibility = View.VISIBLE
            thought.text = algorithm?.thought()
            thought.visibility = View.VISIBLE
        } else {
            thoughtTitle.visibility = View.GONE
            thought.visibility = View.GONE
        }
    }


    private fun getAlgorithm(viewName: String?): IAlgorithm? {

        try {

            val clazz = Class.forName(viewName)
            val constructor = clazz.getConstructor()
            val instance = constructor.newInstance()
            return instance as IAlgorithm
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}