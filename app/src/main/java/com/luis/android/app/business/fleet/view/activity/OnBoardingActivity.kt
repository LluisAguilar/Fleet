package com.luis.android.app.business.fleet.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.fragment.OnBoardingFragment
import com.luis.android.app.business.fleet.view.fragment.PresentationFragment

class OnBoardingActivity : AppCompatActivity() {

    val onboardingFragment = OnBoardingFragment.getInstance()
    val presentationFragment = PresentationFragment.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.onboarding_fragment_container, onboardingFragment)
            .commit()
    }

    fun moveToPresentationFragment() {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right_to_left,
                        R.anim.slide_out_right_to_left
                )
                .replace(R.id.onboarding_fragment_container, presentationFragment)
                .commit()
    }
}