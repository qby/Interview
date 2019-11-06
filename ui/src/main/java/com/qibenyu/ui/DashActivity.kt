package com.qibenyu.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dash.*

class DashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)

        val animator1 = ObjectAnimator.ofFloat(avatarView, "dregee", 270f)
        animator1.duration = 3000

        val animator2 = ObjectAnimator.ofFloat(avatarView, "topFlip", -45f)
        animator2.duration = 1400

        val animator3 = ObjectAnimator.ofFloat(avatarView, "bottomFlip", 45f)
        animator2.duration = 1400

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            animator3, animator1, animator2
        )
        animatorSet.start()

    }
}
