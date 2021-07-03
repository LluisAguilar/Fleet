package com.luis.android.app.business.fleet.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.fragment.OnBoardingPresentationFragment

class OnBoardingActivity : AppCompatActivity() {

    val presentationFragment = OnBoardingPresentationFragment.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.onboarding_fragment_container, presentationFragment)
            .commit()
    }
}