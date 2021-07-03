package com.luis.android.app.business.fleet.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luis.android.app.business.fleet.R

class OnBoardingPresentationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding_presentation, container, false)
    }

    companion object {

        @JvmStatic
        fun getInstance() =
            OnBoardingPresentationFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}