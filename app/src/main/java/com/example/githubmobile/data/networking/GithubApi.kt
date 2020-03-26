package com.example.githubmobile.data.networking

import com.example.githubmobile.data.models.auth.AuthResponse
import com.example.githubmobile.data.models.repos_list.ReposList
import com.example.githubmobile.data.models.user.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.feeds.FeedsLink
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.models.issue.Issue
import com.example.githubmobile.data.models.pull_request.PullRequest
import retrofit2.http.*

interface GithubApi {

    @GET("user")
    suspend fun getUser(@HeaderMap headers: Map<String, String>): User


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


    @GET("feeds")
    suspend fun getFeedsLink(
        @HeaderMap authHeader: Map<String, String>
    ) : FeedsLink

}
