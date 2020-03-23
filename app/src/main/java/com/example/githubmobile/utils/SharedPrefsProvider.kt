package com.example.githubmobile.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val ACCESS_TOKEN_KEY = "access_token_save"
private const val USERNAME_KEY = "username_save"
class SharedPrefsProvider(context: Context){
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveAccessToken(accessToken: String){
        preference.edit().putString(ACCESS_TOKEN_KEY,accessToken).apply()
    }

    fun removeAccessToken(){
        preference.edit().remove(ACCESS_TOKEN_KEY).apply()
    }

    fun loadAccessToken() : String?{
        return preference.getString(ACCESS_TOKEN_KEY, null)
    }


    fun saveUsername(username: String){
        preference.edit().putString(USERNAME_KEY, username).apply()
    }


    fun loadUsername() : String?{
        return preference.getString(USERNAME_KEY, null)
    }
}