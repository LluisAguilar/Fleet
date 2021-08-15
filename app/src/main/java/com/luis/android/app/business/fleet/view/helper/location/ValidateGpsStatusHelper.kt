package com.blumonpay.mpos.utils

import android.content.Context
import android.location.LocationManager

class ValidateGpsStatusHelper {

    companion object {

        fun statusGPS(context: Context): Boolean {
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val status: Boolean
            val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            status = gpsEnabled || networkEnabled
            return status
        }

    }
}