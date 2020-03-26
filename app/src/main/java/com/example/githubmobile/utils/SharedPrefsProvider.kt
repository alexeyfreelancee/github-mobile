package com.example.githubmobile.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

const val ACCESS_TOKEN_KEY = "access_key"
class SharedPrefsProvider(context: Context){
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveAccessKey(accessKey: String){
        preference.edit().putString(ACCESS_TOKEN_KEY,accessKey).apply()
    }

    fun removeAccessKey(){
        preference.edit().remove(ACCESS_TOKEN_KEY).apply()
    }

    fun loadAccessKey() : String?{
        return preference.getString(ACCESS_TOKEN_KEY, "null")
    }
}