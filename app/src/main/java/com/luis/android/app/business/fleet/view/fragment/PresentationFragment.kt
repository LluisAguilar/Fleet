package com.luis.android.app.business.fleet.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luis.android.app.business.fleet.R

class PresentationFragment : Fragment() {

    private lateinit var mView:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mView = inflater.inflate(R.layout.fragment_presentation, container, false)

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
}