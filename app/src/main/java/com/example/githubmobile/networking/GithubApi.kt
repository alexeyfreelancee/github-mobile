package com.example.githubmobile.networking

import com.example.githubmobile.models.AccessToken
import com.example.githubmobile.models.ReposList
import com.example.githubmobile.models.User
import com.example.githubmobile.models.events.UserEvent
import com.example.githubmobile.models.github_repository.GithubRepository
import retrofit2.http.*

interface GithubApi {

    @GET("user")
    suspend fun getUserByToken(@HeaderMap headers: Map<String, String>): User

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): AccessToken


    @GET("users/{user}/repos")
    suspend fun getReposForUser(
        @HeaderMap authHeader: Map<String, String>,
        @Path("user") user: String
    ): ArrayList<GithubRepository>


    @GET("search/repositories")
    suspend fun getReposByName(
        @HeaderMap authHeader: Map<String, String>,
        @Query("q") repoName: String
    ) : ReposList


    @GET("users/{user}/events")
    suspend fun getUserEvents(
        @HeaderMap authHeader:  Map<String, String>,
        @Path("user") user: String): ArrayList<UserEvent>


}
