package com.luis.android.app.business.fleet.view.helper.location

import com.google.android.gms.maps.model.LatLng

interface AddressPositionListener {

    //fun onAdressReceived(adress: String?)
    fun onAdressReceived(myCoordinates: LatLng)
    fun onCityReceived(city: String)

}