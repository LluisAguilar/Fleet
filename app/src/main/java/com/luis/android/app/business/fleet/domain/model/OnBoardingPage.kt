package com.luis.android.app.business.fleet.domain.model

data class OnBoardingPage constructor(val title:String = "", val subtitle:String = "", val body:String = "", val image:String = ""){
    constructor() : this("","","","")
}
