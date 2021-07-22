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

class PresentationFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var quickSearchDescription:TextView
    private lateinit var createBusinessDescription:TextView
    private lateinit var createClientDescription:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_presentation, container, false)

        quickSearchDescription = mView.findViewById(R.id.quick_search_description_tv)
        createBusinessDescription =
            mView.findViewById(R.id.create_business_description_tv)
        createClientDescription =
            mView.findViewById(R.id.create_client_description_tv)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startanimation()
    }

    fun startanimation(){
        runBlocking {
            launch {
                animateDescriptionText(
                    quickSearchDescription,
                    createBusinessDescription,
                    createClientDescription
                )
            }
        }
    }

    private suspend fun animateDescriptionText(
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