package com.qibenyu.algorithm

import android.app.Activity
import android.os.Bundle
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
        answer.text = algorithm?.anwser()
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