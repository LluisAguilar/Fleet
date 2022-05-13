package com.luis.android.app.business.fleet.domain.usecase

import android.app.Application
import com.luis.android.app.business.fleet.data.repositories.repository.AuthenticationRepositoryImpl

class CreateUserAccountUseCase(application: Application) {

    val mAuthenticationRepositoryImpl : AuthenticationRepositoryImpl = AuthenticationRepositoryImpl(application)

    fun createNewUserAccount(email:String, password:String){
        mAuthenticationRepositoryImpl.createNewUserAccount(email, password)
    }
}