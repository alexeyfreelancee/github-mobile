package com.example.githubmobile.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    private val converterFactory : GsonConverterFactory by lazy {
        GsonConverterFactory.create()
    }
    val authentication: GithubApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(converterFactory)
            .build()
            .create(GithubApi::class.java)

    }

    val githubApi: GithubApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(converterFactory)
            .build()
            .create(GithubApi::class.java)

    }


}