package com.hmproductions.valueanimator

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.concurrent.locks.ReentrantLock

class MainActivity : AppCompatActivity() {

    var actualValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animateButton.setOnClickListener {
            startAnimations(numberTextView.text.toString().toInt(), 1000 + actualValue - numberTextView.text.toString().toInt())
            actualValue += 1000

            Handler().postDelayed({
                startAnimations(numberTextView.text.toString().toInt(), 500 + actualValue - numberTextView.text.toString().toInt())
                actualValue += 500},
                500)

        }
    }

    @Synchronized
    private fun startAnimations(oldNumber: Int, add: Int) {

        val animator = ValueAnimator.ofInt(oldNumber, oldNumber + add)
        animator.duration = 2000
        animator.addUpdateListener {
            numberTextView.text = it.animatedValue.toString()
            numberTextView.clearAnimation()
        }
        animator.start()

    }
}
