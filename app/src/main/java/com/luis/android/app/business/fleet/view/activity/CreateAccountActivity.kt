package com.luis.android.app.business.fleet.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.fragment.account.CreateAccountFragment
import com.luis.android.app.business.fleet.view.fragment.account.LoginFragment
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.PAGE_CREATE
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.PAGE_LOGIN
import com.luis.android.app.business.fleet.view.viewmodel.UserDataViewModel

class CreateAccountActivity : BaseActivity() {

    private val mCreateAccountFragment = CreateAccountFragment.getInstance()
    private val mLoginFragment = LoginFragment.getInstance()
    private var mPage = 0
    private lateinit var mUserDataViewModel: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mUserDataViewModel = ViewModelProvider(this).get(UserDataViewModel::class.java)

        mPage = intent.getIntExtra("page", 0)

        when (mPage) {

            PAGE_CREATE -> {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.create_account_container, mCreateAccountFragment)
                        .commit()
            }

            PAGE_LOGIN -> {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.create_account_container, mLoginFragment)
                        .commit()
            }

        }
    }

    fun getUserDataViewModel(): UserDataViewModel{
        return mUserDataViewModel
    }


    override fun onBackPressed() {
        if (mCreateAccountFragment.isVisible) {
            mCreateAccountFragment.onImageBackPressed()
        } else {
            super.onBackPressed()
        }
    }


}