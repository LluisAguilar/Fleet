package com.luis.android.app.business.fleet.view.helper

class StringUtils {

    companion object {
        var DEVICE_LANGUAGE_ID = LANGUAGE_ID.ENGLISH

        const val FIREBASE_DATABASE_INSTANCE = "https://fleet-91204-default-rtdb.firebaseio.com/"
        const val EMPTY_VALUE_STRING = "..."

        const val PAGE_CREATE = 0
        const val PAGE_LOGIN = 1
        const val PAGE_SEARCH = 2

        const val TURN_ON_LOCATION_SERVICE = 100
        const val ACCESS_FINE_LOCATION_REQUEST_CODE = 200

    }

    enum class LANGUAGE_ID {
        ENGLISH,
        SPANISH
    }
}