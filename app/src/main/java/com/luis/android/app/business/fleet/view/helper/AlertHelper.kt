package com.luis.android.app.business.fleet.view.helper

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.google.android.material.internal.ContextUtils.getActivity
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.activity.HomeSearchActivity
import kotlinx.android.synthetic.main.alert_default.*
import java.util.*

class AlertHelper(val context: Context) {


    fun alertPermissionDenied(body: String) {
        val alertDialog = Dialog(context, R.style.Theme_Dialog_Translucent)
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCancelable(true)
        alertDialog.setContentView(R.layout.alert_default)

        alertDialog.alert_icon_close.setOnClickListener {
            alertDialog.dismiss()
            (context as HomeSearchActivity).finish()
        }

        alertDialog.alert_body.setText(body)

        alertDialog.accept_alert_btn.setOnClickListener { view: View? ->
            alertDialog.dismiss()
            (context as HomeSearchActivity).finish()
        }

        alertDialog.setOnCancelListener {
            (context as HomeSearchActivity).finish()
        }

        alertDialog.show()
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(
            Objects.requireNonNull(alertDialog.window)?.attributes
        )
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        alertDialog.window!!.attributes = layoutParams
    }


}