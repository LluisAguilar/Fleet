package com.luis.android.app.business.fleet.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luis.android.app.business.fleet.MethodUtils

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        MethodUtils.setCurrentDeviceLanguage()
    }
}