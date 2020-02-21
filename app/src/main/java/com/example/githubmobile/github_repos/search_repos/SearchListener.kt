package com.example.githubmobile.github_repos.search_repos

import androidx.lifecycle.LiveData
import com.example.githubmobile.models.github_repository.GithubRepository

interface SearchListener {
    fun searchStarted()
    fun searchCompleted(reposList: LiveData<ArrayList<GithubRepository>>)
}