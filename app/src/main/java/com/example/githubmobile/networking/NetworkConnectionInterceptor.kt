package com.example.githubmobile.networking

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http2.ConnectionShutdownException

class NetworkConnectionInterceptor(context: Context) : Interceptor{

    private val applicationContext = context

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isNetworkAvailable()){
            throw ConnectionShutdownException()
        }

        return chain.proceed(chain.request())
    }


    private fun isNetworkAvailable() : Boolean{
        val connectivityManager
                = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it!=null && it.isConnected
        }

    }

}