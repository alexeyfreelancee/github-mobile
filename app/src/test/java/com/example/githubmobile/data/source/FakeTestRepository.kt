package com.example.githubmobile.data.source

import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.AccessToken
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.Repo
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.models.github_repository.Owner
import java.util.ArrayList

class FakeTestRepository : GitRepositoryInterface {
    private val reposList = arrayListOf<GithubRepo>(
        GithubRepo(
            "description",
            Owner("login", "aurl"),
            "lang",
        12,
            "name",
            "url"
        ),
        GithubRepo(
            "description1",
            Owner("login1", "aurl1"),
            "lang1",
            121,
            "name1",
            "url1"
        )
    )

    private val user = User(
        "login",
        "avatar_url",
        "html_url",
        "location",
        "2022-04-18T03:03:03Z",
        23,
        32,
        11
    )
    private val eventList = arrayListOf<UserEvent>(
        UserEvent(
            "type",
            Repo("name", "url"),
            "2022-04-18T03:03:05Z"),
        UserEvent(
            "type1",
            Repo("name1", "url1"),
            "2022-04-18T03:03:07Z"
        )
    )

    private val accessToken = AccessToken(
        "accessToken",
        "tokenType",
        true)



    override suspend fun getUserInfo(): User {
       return user
    }

    override suspend fun getUserEvents(): ArrayList<UserEvent> {
       return eventList
    }

    override suspend fun getReposForUser(): ArrayList<GithubRepo> {
        return reposList
    }

    override suspend fun getReposByName(name: String): ArrayList<GithubRepo> {
        return reposList
    }

    override suspend fun getAccessByCode(code: String): AccessToken {
        return accessToken
    }

    override fun loadAccessTokenFromPrefs(): String? {
        return accessToken.accessToken
    }
}