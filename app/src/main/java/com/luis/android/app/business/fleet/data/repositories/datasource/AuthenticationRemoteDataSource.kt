package com.luis.android.app.business.fleet.data.repositories.datasource

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.luis.android.app.business.fleet.domain.model.UserResponse
import com.luis.android.app.business.fleet.utils.ApplicationUtils.Companion.getActivity

class AuthenticationRemoteDataSource(val application: Application) {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createNewUserAccount(email:String, password:String): MutableLiveData<UserResponse> {
        val createUserResponse = MutableLiveData<UserResponse>()
        getActivity(application)?.let { activity ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, object : OnCompleteListener<AuthResult>{
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            auth.currentUser?.let {
                                createUserResponse.value = UserResponse(true, it.uid,it.email?:"",it.phoneNumber?:"",it.photoUrl.toString(),
                                    "")
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            createUserResponse.value = UserResponse(false,"","","","",
                            task.exception?.message?:"")
                        }
                    }
                })
        }
        return createUserResponse
    }
}