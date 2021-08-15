package com.luis.android.app.business.fleet.view.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.blumonpay.mpos.utils.LocationHelper
import com.google.android.gms.maps.model.LatLng
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.fragment.search.HomeSearchFragment
import com.luis.android.app.business.fleet.view.helper.StringUtils
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.ACCESS_FINE_LOCATION_REQUEST_CODE
import com.luis.android.app.business.fleet.view.helper.location.AddressPositionListener

class HomeSearchActivity : BaseActivity(),  AddressPositionListener {

    private val mSearchFragment = HomeSearchFragment.getInstance()
    private lateinit var locationHelper: LocationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_search)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.home_search_container, mSearchFragment)
            .commit()

        locationHelper = LocationHelper(this, this, this)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == ACCESS_FINE_LOCATION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                locationHelper = LocationHelper(this, this, this)
            } else {
                Log.e("PermissionRequest", "Denied")
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onAdressReceived(myCoordinates: LatLng) {
        Log.e("Coordinates_received", myCoordinates.toString())
    }

    override fun onCityReceived(city: String) {
        Log.e("City_received", city)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == StringUtils.TURN_ON_LOCATION_SERVICE) {
            if (resultCode == RESULT_OK) {
                locationHelper = LocationHelper(this, this, this)
            } else {
                Log.e("TurnOnLocation", "Denied")
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}