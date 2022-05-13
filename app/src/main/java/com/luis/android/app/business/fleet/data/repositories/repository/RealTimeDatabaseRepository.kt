package com.luis.android.app.business.fleet.data.repositories.repository

import androidx.lifecycle.LiveData
import com.luis.android.app.business.fleet.domain.model.OnBoardingPage

interface RealTimeDatabaseRepository {

    fun getOnBoardingDataPages() : LiveData<List<OnBoardingPage>>
}