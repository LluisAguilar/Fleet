package com.luis.android.app.business.fleet.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.domain.model.OnBoardingModel
import com.luis.android.app.business.fleet.view.activity.OnBoardingActivity
import com.luis.android.app.business.fleet.view.adapter.DotsSliderAdapter
import com.luis.android.app.business.fleet.view.adapter.viewpager.OnBoardingViewPagerAdapter
import java.util.ArrayList

class OnBoardingFragment : Fragment(), View.OnClickListener {

    private lateinit var mView:View
    private lateinit var mSliderLayout:LinearLayout
    private lateinit var mDotsSliderAdapter: DotsSliderAdapter
    private lateinit var onboardingViewPager:ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_on_boarding, container, false)
        // Inflate the layout for this fragment
        val onBoardingPagerAdapter =
            context?.let { OnBoardingViewPagerAdapter(getOnBoardingList(), it) }
        onboardingViewPager = mView.findViewById<ViewPager>(R.id.onboarding_viewpager)
        val skipTv = mView.findViewById<TextView>(R.id.skip_tv)
        val nextTv = mView.findViewById<TextView>(R.id.next_tv)
        val backTv = mView.findViewById<TextView>(R.id.back_tv)

        skipTv.setOnClickListener(this)
        nextTv.setOnClickListener(this)
        backTv.setOnClickListener(this)

        onboardingViewPager.adapter = onBoardingPagerAdapter

        //Initialize dots slider
        mSliderLayout = mView.findViewById(R.id.slider_dots)

        mDotsSliderAdapter = DotsSliderAdapter(mSliderLayout, onboardingViewPager.size,onboardingViewPager, requireContext())
        mDotsSliderAdapter.initSlider()

        return mView
    }

    private fun getOnBoardingList(): ArrayList<OnBoardingModel> {
        val onBoardingList = arrayListOf<OnBoardingModel>()

        onBoardingList.add(OnBoardingModel("Text 1", "Body 1", "https://pbs.twimg.com/profile_images/738002943471820800/22-JB9xE_400x400.jpg"))
        onBoardingList.add(OnBoardingModel("Text 2", "Body 2", "https://nordvpn.com/wp-content/uploads/android-vs-ios_1200x675.jpg"))
        onBoardingList.add(OnBoardingModel("Text 3", "Body 3", "https://i.blogs.es/2b63f8/androidze/1366_2000.jpg"))

        return onBoardingList
    }

    companion object {

        @JvmStatic
        fun getInstance() =
            OnBoardingFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.skip_tv ->{
                callPresentationFragment()
            }
            R.id.next_tv -> {
                if (onboardingViewPager.currentItem == onboardingViewPager.size){
                    callPresentationFragment()
                } else {
                    mDotsSliderAdapter.nextPage()
                }
            }
            R.id.back_tv -> {
                mDotsSliderAdapter.lastPage()
            }
        }
    }

    private fun callPresentationFragment() {
        (activity as OnBoardingActivity).moveToPresentationFragment()
    }
}