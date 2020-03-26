package com.example.githubmobile.data

import com.example.githubmobile.data.models.AccessToken

import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.feed.Feed
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.models.issue.Issue
import com.example.githubmobile.data.models.pull_request.PullRequest
import java.util.ArrayList

interface GitRepositoryInterface {
    suspend fun getUserInfo(): User

    suspend fun getUserEvents(): ArrayList<UserEvent>

    suspend fun getReposForUser(): ArrayList<GithubRepo>

    suspend fun getReposByName(name: String): ArrayList<GithubRepo>

    suspend fun getAccessByCode(code: String): AccessToken

    fun loadAccessTokenFromPrefs(): String?

    suspend fun getFeeds() : Feed

    suspend fun getIssues() : ArrayList<Issue>

    suspend fun getPullRequests() : ArrayList<PullRequest>

}