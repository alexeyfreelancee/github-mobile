package com.example.githubmobile.data

import com.example.githubmobile.data.models.AccessToken
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.github_repository.GithubRepo
import java.util.ArrayList

interface GitRepositoryInterface {
    suspend fun getUserInfo(): User

    suspend fun getUserEvents(): ArrayList<UserEvent>

    suspend fun getReposForUser(): ArrayList<GithubRepo>

    suspend fun getReposByName(name: String): ArrayList<GithubRepo>

    suspend fun getAccessByCode(code: String): AccessToken

    suspend fun loadAccessTokenFromPrefs(): String?

    suspend fun getEmptyRepos(): ArrayList<GithubRepo>
}