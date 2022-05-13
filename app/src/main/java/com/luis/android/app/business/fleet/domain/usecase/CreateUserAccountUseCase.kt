package com.luis.android.app.business.fleet.domain.usecase

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.luis.android.app.business.fleet.data.repositories.repository.AuthenticationRepositoryImpl
import com.luis.android.app.business.fleet.domain.model.UserResponse

class CreateUserAccountUseCase(application: Application) {

    val mAuthenticationRepositoryImpl : AuthenticationRepositoryImpl = AuthenticationRepositoryImpl(application)

    fun createNewUserAccount(email:String, password:String): MutableLiveData<UserResponse> {
        return mAuthenticationRepositoryImpl.createNewUserAccount(email, password)
    }
}