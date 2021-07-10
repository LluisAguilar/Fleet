package com.luis.android.app.business.fleet.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luis.android.app.business.fleet.data.repositories.datasource.LocalDataSource
import com.luis.android.app.business.fleet.data.repositories.datasource.RemoteDataSource
import com.luis.android.app.business.fleet.domain.model.OnBoardingPageModel

class FleetRepository(application: Application) {

    private var mRemoteDataSource:RemoteDataSource
    private var mLocalDataSource:LocalDataSource

    init {
        mRemoteDataSource = RemoteDataSource(application)
        mLocalDataSource = LocalDataSource()
    }

    fun getOnBoardingDataPages() : LiveData<List<OnBoardingPageModel>> {
        return mRemoteDataSource.getOnBoardingDataPages()
    }

}