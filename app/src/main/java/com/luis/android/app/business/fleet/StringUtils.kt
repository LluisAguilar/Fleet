package com.luis.android.app.business.fleet

class StringUtils {

    companion object {
        var DEVICE_LANGUAGE_ID = LANGUAGE_ID.ENGLISH

        const val FIREBASE_DATABASE_INSTANCE = "https://fleet-91204-default-rtdb.firebaseio.com/"
        const val EMPTY_VALUE_STRING = "Empty"
    }

    enum class LANGUAGE_ID {
        ENGLISH,
        SPANISH
    }
}