package com.luis.android.app.business.fleet.view.fragment.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.activity.CreateAccountActivity
import com.luis.android.app.business.fleet.view.helper.FieldValidationHelper.Companion.isEmailValid
import com.luis.android.app.business.fleet.view.helper.FieldValidationHelper.Companion.isEmpty
import com.luis.android.app.business.fleet.view.helper.FieldValidationHelper.Companion.isPasswordValid
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
        mView.icon_back_iv.setOnClickListener(this)
        mView.user_icon_back_iv.setOnClickListener(this)


        return mView
    }

    companion object {

        @JvmStatic
        fun getInstance() = CreateAccountFragment()
    }

    override fun onClick(p0: View?) {
        when(p0){

            continue_btn -> {
                validateUserAccountFields()
                if (mView.account_data_layout.visibility == View.VISIBLE){
                    mView.account_data_layout.visibility = View.GONE
                    mView.user_data_layout.visibility = View.VISIBLE
                    mView.continue_btn_tv.text = getString(R.string.create_account)
                }
                val userDataViewModel = (activity as CreateAccountActivity).getUserDataViewModel()

                userDataViewModel.createNewUserAcount("luis@gmail.com", "luis123Aguilar")
            }

            icon_back_iv, user_icon_back_iv -> {
               onImageBackPressed()
            }

        }
    }

    fun onImageBackPressed() {
        if (mView.account_data_layout.visibility == View.VISIBLE){
            activity?.finish()
        } else if (mView.user_data_layout.visibility == View.VISIBLE){
            mView.account_data_layout.visibility = View.VISIBLE
            mView.user_data_layout.visibility = View.GONE
            mView.continue_btn_tv.text = getString(R.string.continue_txt)
        }
    }

    private fun validateUserAccountFields() {

        if (isEmpty(username_et)){

        } else if (!isEmailValid(user_email_et)){

        } else if (!isPasswordValid(user_password_et)){

        } else if (isEmpty(user_password_2_et)) {

        } else if (!user_password_et.text.toString().equals(user_password_2_et.text.toString())){

        } else {
            println("All fields are correct")
        }

    }

}