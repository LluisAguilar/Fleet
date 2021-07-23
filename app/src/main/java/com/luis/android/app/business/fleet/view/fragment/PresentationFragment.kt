package com.luis.android.app.business.fleet.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.luis.android.app.business.fleet.R
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class PresentationFragment : Fragment(), View.OnClickListener {

    private lateinit var mView: View
    private lateinit var quickSearchTv: TextView
    private lateinit var createBusinessTv: TextView
    private lateinit var createClientTv: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_presentation, container, false)

        val quickSearchBtn = mView.findViewById<CardView>(R.id.quick_search_cv)
        val createBusinessBtn = mView.findViewById<CardView>(R.id.create_business_cv)
        val createClientBtn = mView.findViewById<CardView>(R.id.create_account_cv)
        val infoIv = mView.findViewById<ImageView>(R.id.info_iv)

        quickSearchTv = mView.findViewById(R.id.quick_search_description_tv)
        createBusinessTv = mView.findViewById(R.id.create_business_description_tv)
        createClientTv = mView.findViewById(R.id.create_client_description_tv)

        quickSearchBtn.setOnClickListener(this)
        createBusinessBtn.setOnClickListener(this)
        createClientBtn.setOnClickListener(this)
        infoIv.setOnClickListener(this)

        return mView
    }

    companion object {

        @JvmStatic
        fun getInstance() =
                PresentationFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.quick_search_cv -> {

            }
            R.id.create_business_cv -> {


            }
            R.id.create_account_cv -> {


            }
            R.id.info_iv -> {
                if (quickSearchTv.visibility == View.GONE) {
                    CoroutineScope(IO).launch {
                        startAnimSucessive()
                    }
                }
            }
        }
    }

    private suspend fun startAnimSucessive() {
        withContext(IO) {

            val firstTextJob = launch {
                animateDescriptionText(quickSearchTv)
            }
            firstTextJob.join()

            val secondTextJob = async {
                delay(1000)
                animateDescriptionText(createBusinessTv)
            }
            secondTextJob.join()

            val thirdTextJob = async {
                delay(1000)
                animateDescriptionText(createClientTv)
            }.await()
        }
    }

    private fun animateDescriptionText(animatedText: TextView) {
        var anim = AnimationUtils.loadAnimation(context, R.anim.alpha_invisible_to_visible)
        anim.reset()
        anim = AnimationUtils.loadAnimation(context, R.anim.translate_short)
        anim.reset()
        animatedText.clearAnimation()
        animatedText.startAnimation(anim)
        runBlocking {
            launch {
                withContext(Main) {
                    animatedText.visibility = View.VISIBLE
                }
            }
        }

    }
}