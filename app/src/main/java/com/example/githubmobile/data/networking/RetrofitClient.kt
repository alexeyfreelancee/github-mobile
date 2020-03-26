package com.example.githubmobile.data.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitClient {
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val jsonConverterFactory: GsonConverterFactory by lazy {
        GsonConverterFactory.create()
    }

    val githubApi: GithubApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
            .create(GithubApi::class.java)

    }



}