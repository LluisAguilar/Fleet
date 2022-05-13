package com.luis.android.app.business.fleet.utils

import android.app.Activity
import android.app.Application
class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
    }

    private var mCurrentActivity: Activity? = null

    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    fun setCurrentActivity(mCurrentActivity: Activity?) {
        this.mCurrentActivity = mCurrentActivity
    }

}