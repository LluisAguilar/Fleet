package com.luis.android.app.business.fleet.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.domain.model.OnBoardingModel
import com.luis.android.app.business.fleet.view.adapter.viewpager.OnBoardingViewPagerAdapter
import java.util.ArrayList

class OnBoardingPresentationFragment : Fragment() {

    private lateinit var mView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_on_boarding_presentation, container, false)
        // Inflate the layout for this fragment
        val onBoardingPagerAdapter =
            context?.let { OnBoardingViewPagerAdapter(getOnBoardingList(), it) }

        val onboarding_viewpager = mView.findViewById<ViewPager>(R.id.onboarding_viewpager)

        onboarding_viewpager.adapter = onBoardingPagerAdapter

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
            OnBoardingPresentationFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}