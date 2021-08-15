package com.luis.android.app.business.fleet.view.helper

import java.util.*

class MethodUtils {

    companion object {

        fun getStringByLanguage(stringValue: String): String {
            if (!stringValue.equals(StringUtils.EMPTY_VALUE_STRING)) {
                val titleSplit = stringValue.split("|")
                if (StringUtils.DEVICE_LANGUAGE_ID == StringUtils.LANGUAGE_ID.ENGLISH) {
                    return titleSplit[0]
                } else if (StringUtils.DEVICE_LANGUAGE_ID == StringUtils.LANGUAGE_ID.SPANISH) {
                    return titleSplit[1]
                }
                return titleSplit[0]
            } else {
                return stringValue
            }
        }

        fun setCurrentDeviceLanguage() {
            if (Locale.getDefault().language == "en") {
                StringUtils.DEVICE_LANGUAGE_ID = StringUtils.LANGUAGE_ID.ENGLISH
            } else if (Locale.getDefault().language == "es") {
                StringUtils.DEVICE_LANGUAGE_ID = StringUtils.LANGUAGE_ID.SPANISH
            }
        }

    }
}