package com.luis.android.app.business.fleet.data.repositories.repository

import androidx.lifecycle.LiveData
import com.luis.android.app.business.fleet.data.repositories.datasource.RealTimeDatabaseRemoteDataSource
import com.luis.android.app.business.fleet.domain.model.OnBoardingPage

class RealTimeDatabaseRepositoryImpl: RealTimeDatabaseRepository {

    private var mRealTimeDatabaseRemoteDataSource:RealTimeDatabaseRemoteDataSource = RealTimeDatabaseRemoteDataSource()

    override fun getOnBoardingDataPages() : LiveData<List<OnBoardingPage>> {
        return mRealTimeDatabaseRemoteDataSource.getOnBoardingDataPages()
    }

}