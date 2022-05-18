package com.luis.android.app.business.fleet.data.repositories.repository

import androidx.lifecycle.MutableLiveData
import com.luis.android.app.business.fleet.domain.model.UserInformation
import com.luis.android.app.business.fleet.domain.model.UserResponse

interface AuthenticationRepository {

    fun createNewUserAccount(email:String, password:String): MutableLiveData<UserResponse>
    fun addUserAccountData(userInformation: UserInformation):  MutableLiveData<Boolean>
}