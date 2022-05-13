package com.luis.android.app.business.fleet.data.repositories.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.luis.android.app.business.fleet.data.repositories.datasource.AuthenticationRemoteDataSource
import com.luis.android.app.business.fleet.domain.model.UserResponse

class AuthenticationRepositoryImpl(application: Application): AuthenticationRepository {

    private var mAuthenticationRemoteDataSource:AuthenticationRemoteDataSource = AuthenticationRemoteDataSource(application)

    override fun createNewUserAccount(email:String, password:String): MutableLiveData<UserResponse> {
        return mAuthenticationRemoteDataSource.createNewUserAccount(email, password)
    }


}