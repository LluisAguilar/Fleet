package com.luis.android.app.business.fleet.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luis.android.app.business.fleet.domain.model.UserInformation
import com.luis.android.app.business.fleet.domain.model.UserResponse
import com.luis.android.app.business.fleet.domain.usecase.AddUserAccountDataUseCase
import com.luis.android.app.business.fleet.domain.usecase.CreateUserAccountUseCase

class UserDataViewModel(application: Application) : AndroidViewModel(application) {

    private val mCreateUserAccountUseCase: CreateUserAccountUseCase = CreateUserAccountUseCase(application)
    private val mAddUserAccountDataUseCase: AddUserAccountDataUseCase = AddUserAccountDataUseCase(application)

    fun createNewUserAcount(email:String, password:String): LiveData<UserResponse> {
         return mCreateUserAccountUseCase.createNewUserAccount(email, password)
    }

    fun addUserAccountData(userInformation: UserInformation): MutableLiveData<Boolean> {
        return mAddUserAccountDataUseCase.addUserAccountData(userInformation)
    }

}