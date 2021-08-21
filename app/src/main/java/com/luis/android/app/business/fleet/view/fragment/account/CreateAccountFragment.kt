package com.luis.android.app.business.fleet.view.fragment.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luis.android.app.business.fleet.R
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_create_account.view.*


class CreateAccountFragment : Fragment(), View.OnClickListener {

    private lateinit var mView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_create_account, container, false)

        mView.continue_btn.setOnClickListener(this)


        return mView
    }

    companion object {

        @JvmStatic
        fun getInstance() = CreateAccountFragment()
    }

    override fun onClick(p0: View?) {
        when(p0){

            continue_btn -> {
                if (mView.account_data_layout.visibility == View.VISIBLE){
                    mView.account_data_layout.visibility = View.GONE
                    mView.user_data_layout.visibility = View.VISIBLE
                }
            }

        }
    }
}