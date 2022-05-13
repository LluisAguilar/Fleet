package com.luis.android.app.business.fleet.domain.usecase

import androidx.lifecycle.LiveData
import com.luis.android.app.business.fleet.data.repositories.repository.RealTimeDatabaseRepositoryImpl
import com.luis.android.app.business.fleet.domain.model.OnBoardingPage

class GetOnBoardingDataPagesUseCase {

    val realTimeDatabaseRepository : RealTimeDatabaseRepositoryImpl = RealTimeDatabaseRepositoryImpl()

    fun getOnBoardingDataPages() : LiveData<List<OnBoardingPage>> {
        return realTimeDatabaseRepository.getOnBoardingDataPages()
    }

}