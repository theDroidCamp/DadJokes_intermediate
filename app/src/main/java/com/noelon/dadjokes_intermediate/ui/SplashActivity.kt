package com.noelon.dadjokes_intermediate.ui

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.noelon.dadjokes_intermediate.MainActivity
import com.noelon.dadjokes_intermediate.R
import kotlinx.android.synthetic.main.activity_splash.*

//private const val SPLASH_TIME_OUT : Long = 3000
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        Handler().postDelayed({
        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

//        }, SPLASH_TIME_OUT)
    }


}