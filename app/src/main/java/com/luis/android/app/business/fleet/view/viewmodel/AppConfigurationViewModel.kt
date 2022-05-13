package com.luis.android.app.business.fleet.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.luis.android.app.business.fleet.domain.model.OnBoardingPage
import com.luis.android.app.business.fleet.domain.usecase.GetOnBoardingDataPagesUseCase

class AppConfigurationViewModel : ViewModel() {

    val getOnBoardingDataPagesUseCase: GetOnBoardingDataPagesUseCase = GetOnBoardingDataPagesUseCase()

    fun getOnBoardingDataPages() : LiveData<List<OnBoardingPage>> {
        return getOnBoardingDataPagesUseCase.getOnBoardingDataPages()
    }

}