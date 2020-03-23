package com.example.githubmobile.data

import com.example.githubmobile.utils.SharedPrefsProvider
import com.example.githubmobile.data.models.AccessToken
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.source.RemoteDataSource
import java.util.*

class GitRepository(
    private val dataSource: RemoteDataSource,
    private val sharedPrefsProvider: SharedPrefsProvider
) : GitRepositoryInterface {
    private val accessToken =
        mapOf("Authorization" to "token ${sharedPrefsProvider.loadAccessToken()}")
    private val header = mapOf("Authorization" to "token ${sharedPrefsProvider.loadAccessToken()}")
    private val clientId = "3b97901fbec977e5e3f7"
    private val clientSecret = "369cea6f3430594207d0527739ff426356907442"


    override suspend fun getUserInfo(): User {
        val user = dataSource.getUserByToken(accessToken)
        sharedPrefsProvider.saveUsername(user.login)
        return user
    }


    override suspend fun getUserEvents(): ArrayList<UserEvent> {
        val username = getUserInfo().login
        return dataSource.getUserEvents(
            accessToken,
            username
        )
    }


    override suspend fun getReposForUser(): ArrayList<GithubRepo> {
        val username = sharedPrefsProvider.loadUsername()
        username?.let {
            return dataSource.getReposForUser(header, it)
        }
        return ArrayList()
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


    override fun loadAccessTokenFromPrefs(): String? {
        sharedPrefsProvider.loadAccessToken()?.let {
            return it
        }
        return null
    }



}