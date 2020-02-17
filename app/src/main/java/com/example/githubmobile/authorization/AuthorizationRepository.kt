package com.example.githubmobile.authorization

import androidx.lifecycle.LiveData
import com.example.githubmobile.api.RetrofitClient
import com.example.githubmobile.models.AccessToken
import com.example.githubmobile.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object AuthorizationRepository{
    private const val clientId = "Iv1.6d9b7fd23d2a7b19"
    private const val redirectUri = "github://callback"
    private const val clientSecret = "a173cafcddba6061c706bd3fc1a52a5e9d9a27c6"
    private lateinit var accessToken: String


    fun getAccessToken(code: String) : LiveData<AccessToken>{
        return object : LiveData<AccessToken>(){
            override fun onActive() {
                super.onActive()
                CoroutineScope(Dispatchers.IO).launch {
                    accessToken = RetrofitClient.githubApi.getAccessToken(clientId, clientSecret, code).accessToken
                }
            }
        }
    }




}