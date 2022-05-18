package com.luis.android.app.business.fleet.data.repositories.datasource

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.luis.android.app.business.fleet.domain.model.UserInformation


class FireStoreRemoteDataSource(val application: Application) {

    private var fireStoreDB = FirebaseFirestore.getInstance()

    fun addNewUserData(userInformation: UserInformation): MutableLiveData<Boolean> {
        val createNewUserResponse = MutableLiveData<Boolean>()

        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = userInformation.name
        user["last"] = userInformation.lastname
        user["born"] = userInformation.password

// Add a new document with a generated ID
        fireStoreDB.collection("users")
            .document(userInformation.uId)
            .set(user)
            .addOnSuccessListener { documentReference ->
                println("NewUser ${documentReference}")
                createNewUserResponse.value = true
            }
            .addOnFailureListener { e ->
                println("NewUser ${e}")
                createNewUserResponse.value = false
            }

        return createNewUserResponse
    }

}