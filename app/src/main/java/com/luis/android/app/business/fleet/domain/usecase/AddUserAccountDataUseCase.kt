package com.luis.android.app.business.fleet.domain.usecase

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.luis.android.app.business.fleet.data.repositories.repository.AuthenticationRepositoryImpl
import com.luis.android.app.business.fleet.domain.model.UserInformation

class AddUserAccountDataUseCase(application: Application) {

    val mAuthenticationRepositoryImpl : AuthenticationRepositoryImpl = AuthenticationRepositoryImpl(application)

    fun addUserAccountData(userInformation: UserInformation): MutableLiveData<Boolean> {
        return mAuthenticationRepositoryImpl.addUserAccountData(userInformation)
    }
}