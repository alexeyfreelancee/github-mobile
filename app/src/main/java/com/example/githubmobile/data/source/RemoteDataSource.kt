package com.example.githubmobile.data.source

import com.example.githubmobile.data.models.AccessToken
import com.example.githubmobile.data.models.ReposList
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.github_repository.GithubRepo
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
       return retrofit.authentication.getAccessToken(clientId, clientSecret, code)
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
}