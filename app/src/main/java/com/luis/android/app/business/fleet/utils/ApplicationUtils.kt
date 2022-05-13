package com.luis.android.app.business.fleet.utils

import android.app.Activity
import android.app.Application

class ApplicationUtils {

    companion object {

        fun getActivity(application: Application): Activity? {
            return (application.applicationContext as MyApp).getCurrentActivity()
        }

    }

}