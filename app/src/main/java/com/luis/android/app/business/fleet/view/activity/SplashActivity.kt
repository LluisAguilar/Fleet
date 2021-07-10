package com.luis.android.app.business.fleet.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.luis.android.app.business.fleet.MethodUtils
import com.luis.android.app.business.fleet.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MethodUtils.setCurrentDeviceLanguage()
        startAnimations()
    }

    private fun startAnimations() {
        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.reset()
        val l = findViewById<View>(R.id.splash_layout) as LinearLayout
        l.clearAnimation()
        l.startAnimation(anim)
        anim = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim.reset()
        val iv = findViewById<LinearLayout>(R.id.contentSplash)
        iv.clearAnimation()
        iv.startAnimation(anim)

        startThreadIntent()
    }

    private fun startThreadIntent() {
        val splashTread: Thread = object : Thread() {
            override fun run() {
                sleep(3000)
                val intentHome = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                startActivity(intentHome)
                finish()
            }
        }
        splashTread.start()
    }

}