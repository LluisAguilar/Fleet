package com.luis.android.app.business.fleet.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.luis.android.app.business.fleet.domain.model.UserResponse
import com.luis.android.app.business.fleet.domain.usecase.CreateUserAccountUseCase

class UserDataViewModel(application: Application) : AndroidViewModel(application) {

    private val mCreateUserAccountUseCase: CreateUserAccountUseCase = CreateUserAccountUseCase(application)

    fun createNewUserAcount(email:String, password:String): LiveData<UserResponse> {
         return mCreateUserAccountUseCase.createNewUserAccount(email, password)
    }

}