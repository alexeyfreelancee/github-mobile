package com.example.githubmobile.placeholder_activity

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.githubmobile.SharedPrefsProvider
import com.example.githubmobile.models.User
import com.example.githubmobile.networking.GithubApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaceHolderRepository(private val githubApi: GithubApi,
                            private val sharedPrefsProvider: SharedPrefsProvider) {


    fun getUserInfo(): LiveData<User> {
        val accessToken = sharedPrefsProvider.loadAccessToken()
        val headerMap = mapOf("Authorization" to "token $accessToken")
        return object: LiveData<User>(){
            override fun onActive() {
                super.onActive()
                CoroutineScope(IO).launch {
                    val user = githubApi.getUserByToken(headerMap)
                    withContext(Main){
                        value = user
                    }
                }
            }
        }
    }

}