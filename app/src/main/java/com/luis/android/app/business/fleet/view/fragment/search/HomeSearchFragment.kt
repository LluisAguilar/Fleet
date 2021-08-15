package com.luis.android.app.business.fleet.view.fragment.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luis.android.app.business.fleet.R


class HomeSearchFragment : Fragment() {

    private lateinit var mView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home_search, container, false)



        return mView
    }

    companion object {

        @JvmStatic
        fun getInstance() = HomeSearchFragment()
    }
}