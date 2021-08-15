package com.luis.android.app.business.fleet.view.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.EMPTY_VALUE_STRING
import com.luis.android.app.business.fleet.domain.model.OnBoardingPageModel
import com.luis.android.app.business.fleet.domain.viewmodel.AppConfigurationViewModel
import com.luis.android.app.business.fleet.view.activity.OnBoardingActivity
import com.luis.android.app.business.fleet.view.adapter.DotsSliderAdapter
import com.luis.android.app.business.fleet.view.adapter.viewpager.OnBoardingViewPagerAdapter
import java.util.ArrayList

class OnBoardingFragment : Fragment(), View.OnClickListener {

    private lateinit var mView: View
    private lateinit var mSliderLayout: LinearLayout
    private lateinit var mDotsSliderAdapter: DotsSliderAdapter
    private lateinit var onBoardingViewPager: ViewPager
    private lateinit var appConfigViewModel: AppConfigurationViewModel
    private lateinit var onBoardingPagerAdapter: OnBoardingViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appConfigViewModel =
            ViewModelProvider(activity as OnBoardingActivity).get(AppConfigurationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_on_boarding, container, false)
        onBoardingViewPager = mView.findViewById(R.id.onboarding_viewpager)
        val skipTv = mView.findViewById<TextView>(R.id.skip_tv)
        val nextTv = mView.findViewById<TextView>(R.id.next_tv)
        val backTv = mView.findViewById<TextView>(R.id.back_tv)

        // Inflate the layout for this fragment

        initOnBoardingViewPager(arrayListOf(OnBoardingPageModel(EMPTY_VALUE_STRING, EMPTY_VALUE_STRING, EMPTY_VALUE_STRING, EMPTY_VALUE_STRING,)))
        getOnBoardingList()

        skipTv.setOnClickListener(this)
        nextTv.setOnClickListener(this)
        backTv.setOnClickListener(this)

        return mView
    }

    private fun getOnBoardingList() {
        val onBoardingList = arrayListOf<OnBoardingPageModel>()

        appConfigViewModel.getOnBoardingDataPages().observe(activity as OnBoardingActivity, {
            if (it.isNotEmpty()) {
                onBoardingList.clear()
                for (x in 0 until it.size) {
                    onBoardingList.add(it.get(x))
                }
                mDotsSliderAdapter.resetDotsAnim()
                updateOnBoardingAdapter(onBoardingList)
                mDotsSliderAdapter.initSlider()
            }
        })
    }

    private fun updateOnBoardingAdapter(onBoardingList: ArrayList<OnBoardingPageModel>) {
        onBoardingPagerAdapter.updateData(onBoardingList)
        onBoardingViewPager.adapter = onBoardingPagerAdapter
    }

    private fun initOnBoardingViewPager(onBoardingList: ArrayList<OnBoardingPageModel>){
        onBoardingPagerAdapter =
            context?.let { OnBoardingViewPagerAdapter(onBoardingList, it) }!!

        onBoardingViewPager.adapter = onBoardingPagerAdapter

        //Initialize dots slider
        mSliderLayout = mView.findViewById(R.id.slider_dots)

        mDotsSliderAdapter = DotsSliderAdapter(
            mSliderLayout,
            onBoardingViewPager.size,
            onBoardingViewPager,
            requireContext()
        )
        mDotsSliderAdapter.initSlider()
    }

    companion object {

        @JvmStatic
        fun getInstance() =
            OnBoardingFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.skip_tv -> {
                callPresentationFragment()
            }
            R.id.next_tv -> {
                if (onBoardingViewPager.currentItem == onBoardingViewPager.size) {
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