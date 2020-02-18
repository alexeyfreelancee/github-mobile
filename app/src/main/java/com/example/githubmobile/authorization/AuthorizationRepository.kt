package com.example.githubmobile.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubmobile.SharedPrefsProvider
import com.example.githubmobile.networking.AuthGithubApi
import com.example.githubmobile.models.AccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationRepository(private val authGithubApi: AuthGithubApi,
                              private val prefs: SharedPrefsProvider){
    private  val clientId = "Iv1.6d9b7fd23d2a7b19"
    private  val clientSecret = "a173cafcddba6061c706bd3fc1a52a5e9d9a27c6"


    fun getAccessToken(code: String): LiveData<AccessToken> {
        val response = MutableLiveData<AccessToken>()

        CoroutineScope(IO).launch {
            val accessToken = authGithubApi.getAccessToken(clientId, clientSecret, code)

            withContext(Main) {
                response.value = accessToken

                prefs.saveAccessToken(accessToken.accessToken)

            }
        }

        return response
    }




}