package com.example.githubmobile.data

import com.example.githubmobile.data.models.AccessToken
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.feed.Feed
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.models.issue.Issue
import com.example.githubmobile.data.models.pull_request.PullRequest
import com.example.githubmobile.data.source.RemoteDataSource
import com.example.githubmobile.utils.SharedPrefsProvider
import com.example.githubmobile.utils.log

class GitRepository(
    private val dataSource: RemoteDataSource,
    private val sharedPrefsProvider: SharedPrefsProvider
) : GitRepositoryInterface {
    private val accessToken = sharedPrefsProvider.loadAccessToken()
    private val header = mapOf("Authorization" to "token $accessToken")
    private val clientId = "3b97901fbec977e5e3f7"
    private val clientSecret = "369cea6f3430594207d0527739ff426356907442"
    private var username: String? = null

    override suspend fun getUserInfo(): User {
        val user = dataSource.getUserByToken(header)
        sharedPrefsProvider.saveUsername(user.login)
        return user
    }


    override suspend fun getUserEvents(): ArrayList<UserEvent> {
        val username = getUserInfo().login
        return dataSource.getUserEvents(
            header,
            username
        )
    }


    override suspend fun getReposForUser(): ArrayList<GithubRepo> {
        val username = username ?: provideUserName()
        return dataSource.getReposForUser(header, username)
    }


    override suspend fun getReposByName(name: String): ArrayList<GithubRepo> {

        return dataSource.getReposByName(header, name).items
    }


    override suspend fun getAccessByCode(code: String): AccessToken {
        val accessToken = try {
            dataSource.getAccessToken(clientId, clientSecret, code)
        } catch (ex: Exception) {
            AccessToken(success = false)
        }
        sharedPrefsProvider.saveAccessToken(accessToken.accessToken)
        return accessToken
    }


    override suspend fun getFeeds(): Feed {
        val username = username ?: provideUserName()
        return dataSource.getFeeds(header, accessToken!!, username)
    }


    override suspend fun getIssues(): ArrayList<Issue> {
        val username = username ?: provideUserName()
        val reposList = dataSource.getReposForUser(
            header, username
        )
        val issueList = ArrayList<Issue>()
        reposList.forEach {
            val issues = dataSource.getIssues(header, username, it.name)

            if (issues.size > 0) issueList.addAll(issues)
        }
        return issueList
    }

    override suspend fun getPullRequests(): ArrayList<PullRequest> {
        val username = username ?: provideUserName()
        val reposList = dataSource.getReposForUser(
            header, username
        )
        val pullRequestList = ArrayList<PullRequest>()
        reposList.forEach {
            val pullRequests = dataSource.getPullRequests(header, username, it.name)
            if(pullRequests.size > 0) pullRequestList.addAll(pullRequests)
        }
        return pullRequestList
    }

    override fun loadAccessTokenFromPrefs(): String? {
        sharedPrefsProvider.loadAccessToken()?.let {
            return it
        }
        return null
    }

    private suspend fun provideUserName(): String {
        val result = getUserInfo().login
        this.username = result
        return result
    }

}