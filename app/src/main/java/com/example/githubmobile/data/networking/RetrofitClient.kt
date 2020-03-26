package com.example.githubmobile.data.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val converterFactory : GsonConverterFactory by lazy {
        GsonConverterFactory.create()
    }
    val github: GithubApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(converterFactory)
            .build()
            .create(GithubApi::class.java)

    }

    val githubApi: GithubApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(GithubApi::class.java)

    }


}