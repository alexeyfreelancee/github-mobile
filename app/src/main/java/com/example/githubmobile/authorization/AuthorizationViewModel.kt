package com.example.githubmobile.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class AuthorizationViewModel(private val repository: AuthorizationRepository) : ViewModel(){
    var authorizationListener: AuthorizationListener? = null
    lateinit var accessToken: LiveData<String>
    fun callbackHandled(code: String){
        val accessToken = repository.getAccessByCode(code)
        authorizationListener?.successAuthorization(accessToken)
    }

    fun getAccessToken(){
        accessToken = repository.loadAccessTokenFromPrefs()

    }



}