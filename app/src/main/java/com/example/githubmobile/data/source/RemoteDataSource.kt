package com.example.githubmobile.data.source

import com.example.githubmobile.data.models.AccessToken
import com.example.githubmobile.data.models.ReposList
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.feed.Feed
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.models.issue.Issue
import com.example.githubmobile.data.models.pull_request.PullRequest
import com.example.githubmobile.data.networking.GithubApi
import com.example.githubmobile.data.networking.RetrofitClient

class RemoteDataSource(private val retrofit: RetrofitClient) : GithubApi {
    override suspend fun getUserByToken(headers: Map<String, String>): User {
        return retrofit.githubApi.getUserByToken(headers)
    }

    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessToken {
       return retrofit.github.getAccessToken(clientId, clientSecret, code)
    }

    override suspend fun getReposForUser(
        authHeader: Map<String, String>,
        user: String
    ): ArrayList<GithubRepo> {
      return retrofit.githubApi.getReposForUser(authHeader, user)
    }

    override suspend fun getReposByName(
        authHeader: Map<String, String>,
        repoName: String
    ): ReposList {
        return retrofit.githubApi.getReposByName(authHeader, repoName)
    }

    override suspend fun getUserEvents(
        authHeader: Map<String, String>,
        user: String
    ): ArrayList<UserEvent> {
        return retrofit.githubApi.getUserEvents(authHeader, user)
    }

    override suspend fun getPullRequests(
        authHeader: Map<String, String>,
        user: String,
        repoName: String
    ): ArrayList<PullRequest> {
        return retrofit.githubApi.getPullRequests(authHeader, user, repoName)
    }

    override suspend fun getFeeds(authHeader: Map<String,String>,username: String, accessToken: String): Feed {
        return retrofit.github.getFeeds(authHeader,username, accessToken)
    }


    override suspend fun getIssues(authHeader: Map<String,String>,user: String, repoName: String): ArrayList<Issue> {
        return retrofit.githubApi.getIssues(authHeader,user, repoName)
    }

}