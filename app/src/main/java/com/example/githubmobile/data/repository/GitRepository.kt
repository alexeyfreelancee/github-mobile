package com.example.githubmobile.data.repository

import android.util.Base64
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.models.issue.Issue
import com.example.githubmobile.data.models.pull_request.PullRequest
import com.example.githubmobile.data.models.user.User
import com.example.githubmobile.data.networking.RetrofitClient
import com.example.githubmobile.utils.SharedPrefsProvider
import com.sun.syndication.feed.synd.SyndEntry
import com.sun.syndication.io.SyndFeedInput
import com.sun.syndication.io.XmlReader
import java.net.URL


class GitRepository(
    private val retrofit: RetrofitClient,
    private val sharedPrefsProvider: SharedPrefsProvider
) {
    private var header = mapOf("Authorization" to "Basic ${sharedPrefsProvider.loadAccessKey()}")
    private var username: String? = null

    suspend fun authorize(username: String, pass: String): Boolean {
        val base = "$username:$pass"
        val accessKey = Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
        header = mapOf("Authorization" to "Basic $accessKey")
        return try {
            retrofit.githubApi.getUser(header)
            sharedPrefsProvider.saveAccessKey(accessKey)
            true
        } catch (ex: Exception) {
            false
        }
    }


    suspend fun getUserInfo(): User {
        return retrofit.githubApi.getUser(header)
    }


    suspend fun getUserEvents(): ArrayList<UserEvent> {
        val username = getUserInfo().login
        return retrofit.githubApi.getUserEvents(header, username)
    }


    suspend fun getReposForUser(): ArrayList<GithubRepo> {
        val username = username ?: provideUserName()
        return retrofit.githubApi.getReposForUser(header, username)
    }


    suspend fun getReposByName(name: String): ArrayList<GithubRepo> {

        return retrofit.githubApi.getReposByName(header, name).items
    }


    suspend fun getFeeds(): ArrayList<SyndEntry> {
        val link = retrofit.githubApi.getFeedsLink(header).Links.currentUser.href
        val feeds = SyndFeedInput().build(XmlReader(URL(link)))
        val feedsList = ArrayList<SyndEntry>()
        feeds.entries.forEach {
            feedsList.add(it as SyndEntry)
        }

        return feedsList
    }


    suspend fun getIssues(): ArrayList<Issue> {
        val username = username ?: provideUserName()
        val reposList = retrofit.githubApi.getReposForUser(header, username)
        val issueList = ArrayList<Issue>()
        reposList.forEach {
            val issues = retrofit.githubApi.getIssues(header, username, it.name)
            issues.forEach { issue ->
                if (issue.pullRequest?.url == null) issueList.add(issue)
            }
        }
        return issueList
    }

    suspend fun getPullRequests(): ArrayList<PullRequest> {
        val username = username ?: provideUserName()
        val reposList = retrofit.githubApi.getReposForUser(header, username)
        val pullRequestList = ArrayList<PullRequest>()
        reposList.forEach {
            val pullRequests = retrofit.githubApi.getPullRequests(header, username, it.name)
            if (pullRequests.size > 0) pullRequestList.addAll(pullRequests)
        }
        return pullRequestList
    }

    fun loadSecretKey(): String? {
        return sharedPrefsProvider.loadAccessKey()
    }

    private suspend fun provideUserName(): String {
        val result = getUserInfo().login
        this.username = result
        return result
    }

}