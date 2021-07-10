package com.luis.android.app.business.fleet.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luis.android.app.business.fleet.data.repositories.FleetRepository
import com.luis.android.app.business.fleet.domain.model.OnBoardingPageModel

class AppConfigurationViewModel(application: Application) : AndroidViewModel(application) {

    val fleetRepository : FleetRepository = FleetRepository(application)

    fun getOnBoardingDataPages() : LiveData<List<OnBoardingPageModel>> {
        return fleetRepository.getOnBoardingDataPages()
    }

}