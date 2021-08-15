package com.luis.android.app.business.fleet.data.repositories.datasource

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.EMPTY_VALUE_STRING
import com.luis.android.app.business.fleet.view.helper.StringUtils.Companion.FIREBASE_DATABASE_INSTANCE
import com.luis.android.app.business.fleet.domain.model.OnBoardingPageModel

class RemoteDataSource(application: Application) {

    private val mFirebaseDb = FirebaseDatabase.getInstance(FIREBASE_DATABASE_INSTANCE)
    private val mFirebaseReference = mFirebaseDb.reference

    fun getOnBoardingDataPages() : MutableLiveData<List<OnBoardingPageModel>> {
        val pagesList = MutableLiveData<List<OnBoardingPageModel>>()

        mFirebaseReference.child("On boarding").child("ScreenPages").orderByKey().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.map { data -> data.getValue(OnBoardingPageModel::class.java) }.map {
                    it.let { it1 ->
                        OnBoardingPageModel(
                            title = it?.title ?: EMPTY_VALUE_STRING,
                            subtitle = it?.subtitle  ?: EMPTY_VALUE_STRING,
                            body = it?.body  ?: EMPTY_VALUE_STRING,
                            image = it?.image  ?: EMPTY_VALUE_STRING
                        )
                    }
                }
                pagesList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                print("Error: " + error.message)
            }

        })

        return pagesList
    }
}