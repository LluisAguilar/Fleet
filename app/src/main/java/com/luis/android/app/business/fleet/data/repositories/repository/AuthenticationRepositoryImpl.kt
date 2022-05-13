package com.luis.android.app.business.fleet.data.repositories.repository

import android.app.Application
import com.luis.android.app.business.fleet.data.repositories.datasource.AuthenticationRemoteDataSource

class AuthenticationRepositoryImpl(application: Application): AuthenticationRepository {

    private var mAuthenticationRemoteDataSource:AuthenticationRemoteDataSource = AuthenticationRemoteDataSource(application)

    override fun createNewUserAccount(email:String, password:String) {
        mAuthenticationRemoteDataSource.createNewUserAccount(email, password)
    }


}