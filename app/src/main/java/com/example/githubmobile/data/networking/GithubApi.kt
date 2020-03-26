package com.example.githubmobile.data.networking

import com.example.githubmobile.data.models.AccessToken
import com.example.githubmobile.data.models.ReposList
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.feed.Feed
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.models.issue.Issue
import com.example.githubmobile.data.models.pull_request.PullRequest
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
    ): ArrayList<GithubRepo>


    @GET("search/repositories")
    suspend fun getReposByName(
        @HeaderMap authHeader: Map<String, String>,
        @Query("q") repoName: String
    ) : ReposList


    @GET("users/{user}/events")
    suspend fun getUserEvents(
        @HeaderMap authHeader:  Map<String, String>,
        @Path("user") user: String): ArrayList<UserEvent>

    @GET("repos/{user}/{repo}/pulls")
    suspend fun getPullRequests(
        @HeaderMap authHeader:  Map<String, String>,
        @Path("user") user: String,
        @Path("repo") repoName: String) : ArrayList<PullRequest>

    @GET("repos/{user}/{repo}/issues")
    suspend fun getIssues(
        @HeaderMap authHeader:  Map<String, String>,
        @Path("user") user: String,
        @Path("repo") repoName: String
    ) : ArrayList<Issue>


    @GET("{username}.private.actor.atom")
    suspend fun getFeeds(
        @HeaderMap authHeader:  Map<String, String>,
        @Path("username") username: String,
        @Query("token") accessToken: String
    ) : Feed
}
