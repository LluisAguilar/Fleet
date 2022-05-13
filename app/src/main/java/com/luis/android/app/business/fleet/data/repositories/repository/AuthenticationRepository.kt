package com.luis.android.app.business.fleet.data.repositories.repository

interface AuthenticationRepository {

    fun createNewUserAccount(email:String, password:String)
}