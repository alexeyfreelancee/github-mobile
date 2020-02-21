package com.example.githubmobile.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubmobile.SharedPrefsProvider
import com.example.githubmobile.models.User
import com.example.githubmobile.models.events.UserEvent
import com.example.githubmobile.networking.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class UserRepository(
    private val retrofitClient: RetrofitClient,
    private val sharedPrefsProvider: SharedPrefsProvider
) {
    val accessToken = mapOf("Authorization" to "token ${sharedPrefsProvider.loadAccessToken()}")

    fun getUserInfo(): LiveData<User> {
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                CoroutineScope(IO).launch {
                    val user = retrofitClient.githubApi.getUserByToken(accessToken)

                    withContext(Main) {
                        value = user.apply {
                            created_at = "Joined at ${ getDate(created_at)}"
                            sharedPrefsProvider.saveUsername(login)
                        }
                    }
                }
            }
        }
    }


    fun getDate(string: String): String{
        val dateString = string.replace("[TZ]".toRegex(), " ")
        val date=SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).parse(dateString)
        val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return simpleDateFormat.format(date)
    }



    suspend fun getUserEvents(): LiveData<ArrayList<UserEvent>>{
        val result = MutableLiveData<ArrayList<UserEvent>>()

        val response = retrofitClient.githubApi.getUserEvents(accessToken, sharedPrefsProvider.loadUsername()!!)
        response.forEach {
            it.created_at = getDate(it.created_at)
        }
        result.value = response
        return result

    }




}