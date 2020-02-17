package com.example.githubmobile.api

import com.example.githubmobile.models.AccessToken
import retrofit2.http.*

interface GithubApi {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(@Field("client_id") clientId: String,
                               @Field("client_secret") clientSecret: String,
                               @Field("code") code: String) : AccessToken
}