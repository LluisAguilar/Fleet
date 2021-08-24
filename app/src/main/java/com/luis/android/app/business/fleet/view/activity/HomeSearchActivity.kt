package com.luis.android.app.business.fleet.view.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import com.blumonpay.mpos.utils.LocationHelper
import com.google.android.gms.maps.model.LatLng
import com.luis.android.app.business.fleet.R
import com.luis.android.app.business.fleet.view.fragment.search.HomeSearchFragment
import com.luis.android.app.business.fleet.view.helper.AlertHelper
import com.luis.android.app.business.fleet.view.helper.StringUtils
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.ACCESS_FINE_LOCATION_REQUEST_CODE
import com.luis.android.app.business.fleet.view.helper.location.AddressPositionListener

class HomeSearchActivity : BaseActivity(),  AddressPositionListener {

    private val mSearchFragment = HomeSearchFragment.getInstance()
    private lateinit var mLocationHelper: LocationHelper
    private lateinit var mAlertHelper: AlertHelper
    private lateinit var mLocationStringBuilder:StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_search)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.home_search_container, mSearchFragment)
            .commit()

        mLocationHelper = LocationHelper(this, this, this)
        mAlertHelper = AlertHelper(this)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == ACCESS_FINE_LOCATION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                mLocationHelper = LocationHelper(this, this, this)
            } else {
                mAlertHelper.alertPermissionDenied("Permissions denied")
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onAdressReceived(myCoordinates: LatLng) {
        mLocationStringBuilder = StringBuilder()
        mLocationStringBuilder.clear()
        mLocationStringBuilder.append(myCoordinates.toString())
        mSearchFragment.setUserLocationText(mLocationStringBuilder.toString())
    }

    override fun onCityReceived(city: String, fullAddress:String) {
        mLocationStringBuilder.append(" : $fullAddress - City: $city")
        mSearchFragment.setUserLocationText(mLocationStringBuilder.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == StringUtils.TURN_ON_LOCATION_SERVICE) {
            if (resultCode == RESULT_OK) {
                mLocationHelper = LocationHelper(this, this, this)
            } else {
                mAlertHelper.alertPermissionDenied("Permissions denied")
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}