package com.luis.android.app.business.fleet.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luis.android.app.business.fleet.utils.MyApp
import com.luis.android.app.business.fleet.view.helper.MethodUtils

open class BaseActivity : AppCompatActivity() {
    protected var mMyApp: MyApp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMyApp = this.applicationContext as MyApp
    }

    override fun onResume() {
        super.onResume()
        MethodUtils.setCurrentDeviceLanguage()
        mMyApp?.setCurrentActivity(this)
    }

    override fun onPause() {
        clearReferences()
        super.onPause()
    }

    override fun onDestroy() {
        clearReferences()
        super.onDestroy()
    }

    private fun clearReferences() {
        val currActivity: Activity? = mMyApp?.getCurrentActivity()
        if (this == currActivity) mMyApp?.setCurrentActivity(null)
    }
}