package com.luis.android.app.business.fleet.view.helper

import android.content.Context
import android.content.Intent
import com.luis.android.app.business.fleet.view.activity.CreateAccountActivity
import com.luis.android.app.business.fleet.view.activity.HomeSearchActivity
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.PAGE_CREATE
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.PAGE_LOGIN
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.PAGE_SEARCH


fun navigatePresentationToCreateAccountActivity(context: Context, pageIndicator: Int) {

    if (pageIndicator == PAGE_CREATE) {
        val intent = Intent(context, CreateAccountActivity::class.java)
        intent.putExtra("page", pageIndicator)
        context.startActivity(intent)
    } else if (pageIndicator == PAGE_LOGIN) {
        val intent = Intent(context, CreateAccountActivity::class.java)
        intent.putExtra("page", pageIndicator)
        context.startActivity(intent)
    } else if (pageIndicator == PAGE_SEARCH) {
        val intent = Intent(context, HomeSearchActivity::class.java)
        context.startActivity(intent)
    }
}