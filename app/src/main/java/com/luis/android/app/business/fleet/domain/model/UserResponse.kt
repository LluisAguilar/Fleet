package com.luis.android.app.business.fleet.domain.model

data class UserResponse(
    val response:Boolean,
    val uId:String,
    val email:String,
    val phoneNumber:String,
    val photoUrl:String,
    val error:String)
