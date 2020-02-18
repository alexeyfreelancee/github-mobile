package com.example.githubmobile.networking

import com.example.githubmobile.models.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface GithubApi {

    @Headers("Authorization: token OAUTH-TOKEN")
    @GET("user")
    @FormUrlEncoded
    suspend fun getUserByToken(@HeaderMap headers: Map<String, String>): User


    companion object {

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): AuthGithubApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthGithubApi::class.java)
        }


    }
}