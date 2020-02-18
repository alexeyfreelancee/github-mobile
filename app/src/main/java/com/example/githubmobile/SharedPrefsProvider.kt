package com.example.githubmobile

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val ACCESS_TOKEN_KEY = "access_token_save"
class SharedPrefsProvider(context: Context){
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveAccessToken(accessToken: String){
        preference.edit().putString(ACCESS_TOKEN_KEY,accessToken).apply()
    }


    fun loadAccessToken() : String?{
        return preference.getString(ACCESS_TOKEN_KEY, null)
    }
}