package com.example.githubmobile.authorization

import androidx.lifecycle.ViewModel

class AuthorizationViewModel(private val repository: AuthorizationRepository) : ViewModel(){
    var authorizationListener: AuthorizationListener? = null

    fun callbackHandled(code: String){
        val accessToken = repository.getAccessToken(code)
        authorizationListener?.successAuthorization(accessToken)
    }



}