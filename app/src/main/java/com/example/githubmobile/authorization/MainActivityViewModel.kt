package com.example.githubmobile.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubmobile.models.AccessToken

class MainActivityViewModel : ViewModel(){
    private val code: MutableLiveData<String> = MutableLiveData()

    val accessToken: LiveData<AccessToken> = Transformations
        .switchMap(code){
            AuthorizationRepository.getAccessToken(it)
        }


    fun getAccessToken(code: String){
        val update = code
        if(this.code.value == code){
            return
        }

        this.code.value = update
    }



}