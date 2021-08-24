package com.luis.android.app.business.fleet.view.helper

import android.util.Patterns
import android.widget.EditText
import java.util.regex.Pattern

class FieldValidationHelper {

    companion object {

        fun isEmpty(editText:EditText) : Boolean {
            if (editText.text == null){
                return true
            } else if (editText.text.isEmpty()){
                return true
            } else return editText.text.equals("")
        }

        fun isEmailValid(editText: EditText) : Boolean {
            if (!isEmpty(editText)){
                val email:String = editText.text.toString()
                val pattern = Patterns.EMAIL_ADDRESS
                return pattern.matcher(email).matches()
            } else {
                return false
            }
        }

        fun isPasswordValid(editText: EditText): Boolean {
            if (!isEmpty(editText)) {
                val password = editText.text.toString()
                val number = password.trim { it <= ' ' }
                return number.matches("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,50}\$".toRegex())
            } else {
                return false
            }
        }

        fun isPhoneValid(editText: EditText): Boolean {
            if (!isEmpty(editText)) {
                val phone = editText.text.toString()
                val expresion = "^[0-9]{10}\$"
                val pattern = Pattern.compile(expresion, Pattern.CASE_INSENSITIVE)
                val matcher = pattern.matcher(phone)
                return matcher.matches()
            } else {
                return false
            }
        }


    }
}