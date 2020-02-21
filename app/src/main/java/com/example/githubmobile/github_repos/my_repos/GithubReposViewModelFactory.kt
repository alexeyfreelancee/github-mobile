package com.example.githubmobile.github_repos.my_repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.github_repos.GithubReposRepository

class GithubReposViewModelFactory (
    private val repository: GithubReposRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubReposViewModel(
            repository
        ) as T
    }

}