package com.example.githubmobile.networking

import com.example.githubmobile.models.AccessToken
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AuthGithubApi{

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(@Field("client_id") clientId: String,
                               @Field("client_secret") clientSecret: String,
                               @Field("code") code: String) : AccessToken





    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): AuthGithubApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

           return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthGithubApi::class.java)
        }


    }



}