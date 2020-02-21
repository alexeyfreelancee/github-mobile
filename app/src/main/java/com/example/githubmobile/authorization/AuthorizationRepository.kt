package com.example.githubmobile.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubmobile.SharedPrefsProvider
import com.example.githubmobile.models.AccessToken
import com.example.githubmobile.networking.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationRepository(
    private val retrofitClient: RetrofitClient,
    private val prefs: SharedPrefsProvider) {
    private val clientId = "3b97901fbec977e5e3f7"
    private val clientSecret = "369cea6f3430594207d0527739ff426356907442"


    fun getAccessByCode(code: String): LiveData<AccessToken> {
        val response = MutableLiveData<AccessToken>()

        CoroutineScope(IO).launch {
            val accessToken = try {
                retrofitClient.authentication.getAccessToken(clientId, clientSecret, code)
            } catch (e: Exception) {
                AccessToken(success = false)
            }


            withContext(Main) {

                response.value = accessToken
                prefs.saveAccessToken(accessToken.accessToken)

            }
        }

        return response
    }


    fun loadAccessTokenFromPrefs() : LiveData<String>{
        return object: LiveData<String>(){
            override fun onActive() {
                super.onActive()
                value = prefs.loadAccessToken()
            }
        }
    }


}