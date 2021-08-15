package com.luis.android.app.business.fleet.view.activity

import android.os.Bundle
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.fragment.account.CreateAccountFragment
import com.luis.android.app.business.fleet.view.fragment.account.LoginFragment
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.PAGE_CREATE
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.PAGE_LOGIN

class CreateAccountActivity : BaseActivity() {

    private val mCreateAccountFragment = CreateAccountFragment.getInstance()
    private val mLoginFragment = LoginFragment.getInstance()
    private var mPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mPage = intent.getIntExtra("page",0)

        when(mPage){

            PAGE_CREATE -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.create_account_container,mCreateAccountFragment)
                    .commit()
            }

            PAGE_LOGIN -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.create_account_container,mLoginFragment)
                    .commit()
            }

        }
    }


}