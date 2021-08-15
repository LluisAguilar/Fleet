package com.blumonpay.mpos.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.luis.android.app.business.fleet.view.helper.StringUtils
import com.luis.android.app.business.fleet.view.helper.location.AddressPositionListener
import java.io.IOException
import java.util.*

class LocationHelper(
    activity: Activity,
    context: Context,
    adressPositionListener: AddressPositionListener
) {

    private var activity: Activity? = null
    private var context:Context? = null
    private var myAdress: String? = null
    private var adressPositionListener:AddressPositionListener
    var locationManager: LocationManager? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null

    init {
        this.activity = activity
        this.context = context
        this.adressPositionListener = adressPositionListener
        initLocation()
    }


    private fun initLocation() {
        locationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val mCurrentLocation = locationResult.lastLocation
                val myCoordinates = LatLng(mCurrentLocation.latitude, mCurrentLocation.longitude)
                adressPositionListener.onAdressReceived(myCoordinates)
                getCityName(myCoordinates)
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                Log.e("LocationAvailability", p0.toString())
                super.onLocationAvailability(p0)
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("mylog", "Getting Location Permission")
            if (activity!!.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("mylog", "Not granted")
                activity!!.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), StringUtils.ACCESS_FINE_LOCATION_REQUEST_CODE)
            } else {
                validateGps()
            }
        } else {
            validateGps()
        }
    }

    fun validateGps(): Boolean {
        return if (ValidateGpsStatusHelper.statusGPS(context!!)) {
            Log.e("myLog", "GPS is enabled")
            requestLocation()
            true
        } else {
            Log.e("myLog", "GPS is disabled")
            forceEnableGps(GoogleApiHelper(activity!!))
            false
        }
    }

    private fun forceEnableGps(googleApiHelper: GoogleApiHelper) {
        googleApiHelper.setConnectionListener(object : GoogleApiHelper.ConnectionListener {
            override fun onConnectionFailed(connectionResult: ConnectionResult) {
                //Connection Failed
                Log.e("mylog", connectionResult.errorMessage.toString())
            }

            override fun onConnectionSuspended(i: Int) {
                //Connection Suspended
                Log.e("mylog", i.toString())
            }

            override fun onConnected(bundle: Bundle?) {
                locationRequest = LocationRequest.create()
                locationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                locationRequest!!.setInterval(20 * 1000.toLong())
                locationRequest!!.setFastestInterval(3 * 1000.toLong())
                val builder = LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest!!)
                builder.setAlwaysShow(true)
                val result = LocationServices.getSettingsClient(
                    activity!!
                ).checkLocationSettings(builder.build())
                result.addOnCompleteListener { task: Task<LocationSettingsResponse?> ->
                    try {
                        val response = task.getResult(
                            ApiException::class.java
                        )
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                    } catch (exception: ApiException) {
                        when (exception.statusCode) {
                            LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->                                     // LocationFirst settings are not satisfied. But could be fixed by showing the
                                // user a dialog.
                                try {
                                    // Cast to a resolvable exception.
                                    val resolvable =
                                        exception as ResolvableApiException
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    resolvable.startResolutionForResult(
                                        activity,
                                        StringUtils.TURN_ON_LOCATION_SERVICE
                                    )
                                } catch (e: SendIntentException) {
                                    // Ignore the error.
                                    Log.e("mylog", e.message.toString())
                                } catch (e: ClassCastException) {
                                    // Ignore, should be an impossible error.
                                    Log.e("mylog", e.message.toString())
                                }
                            LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            }
                            else -> Log.i("OnConnected", "No Selecciono Permiso")
                        }
                    }
                }
            }
        })
    }

    fun requestLocation() {
        val criteria = Criteria()
        criteria.accuracy = criteria.accuracy
        criteria.powerRequirement = Criteria.POWER_MEDIUM
        val provider = locationManager!!.getBestProvider(criteria, true)
        if (ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity!!, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val location = locationManager!!.getLastKnownLocation(provider!!)
        Log.d("mylog", "In Requesting Location")
        if (location != null && System.currentTimeMillis() - location.time <= 1000 * 2) {
            val myCoordinates = LatLng(location.latitude, location.longitude)
            adressPositionListener.onAdressReceived(myCoordinates)
            getCityName(myCoordinates)
        } else {
            val locationRequest = LocationRequest()
            locationRequest.numUpdates = 1
            locationRequest.interval = 1
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            Log.d("mylog", "Last location too old getting new location!")
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
            mFusedLocationClient!!.requestLocationUpdates(
                locationRequest,
                mLocationCallback, Looper.myLooper()
            )
        }
    }

    private fun getCityName(myCoordinates: LatLng) {
        val geocoder = Geocoder(activity, Locale.getDefault())
        try {
            val addresses =
                geocoder.getFromLocation(myCoordinates.latitude, myCoordinates.longitude, 1)
            myAdress = addresses[0].getAddressLine(0)
            Log.d("mylog", "Complete Address: $addresses")
            Log.d("mylog", "Address: $myAdress")
            myAdress?.let {
                adressPositionListener.onCityReceived(addresses[0].locality)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            adressPositionListener.onCityReceived("error")
        }
    }
}