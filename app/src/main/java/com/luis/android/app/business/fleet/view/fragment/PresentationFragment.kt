package com.luis.android.app.business.fleet.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.luis.android.app.business.fleet.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.schedule

class PresentationFragment : Fragment() {

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_presentation, container, false)

        val quickSearchDescription = mView.findViewById<TextView>(R.id.quick_search_description_tv)
        val createBusinessDescription =
            mView.findViewById<TextView>(R.id.create_business_description_tv)
        val createClientDescription =
            mView.findViewById<TextView>(R.id.create_client_description_tv)

        runBlocking {
            launch {
                animateDescriptionText(
                    quickSearchDescription,
                    createBusinessDescription,
                    createClientDescription
                )
            }
        }

        return mView
    }

    suspend fun animateDescriptionText(
        animatedText: TextView,
        animatedText2: TextView,
        animatedText3: TextView
    ) {
        val constraintContainer =
            mView.findViewById<ConstraintLayout>(R.id.constraint_layout_container)
        var anim = AnimationUtils.loadAnimation(context, R.anim.alpha_invisible_to_visible)
        anim.reset()
        constraintContainer.clearAnimation()
        constraintContainer.startAnimation(anim)
        anim = AnimationUtils.loadAnimation(context, R.anim.translate_short)
        anim.reset()

        delay(1000L)
        animatedText.startAnimation(anim)
        animatedText.visibility = View.VISIBLE

        delay(1000L)
        animatedText.clearAnimation()
        animatedText2.startAnimation(anim)
        animatedText2.visibility = View.VISIBLE

        delay(1000L)
        animatedText2.clearAnimation()
        animatedText3.startAnimation(anim)
        animatedText3.visibility = View.VISIBLE

    }

    companion object {

        @JvmStatic
        fun getInstance() =
            PresentationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}