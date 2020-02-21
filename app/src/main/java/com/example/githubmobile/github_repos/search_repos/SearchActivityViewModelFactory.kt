package com.example.githubmobile.github_repos.search_repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.github_repos.GithubReposRepository

class SearchActivityViewModelFactory  (
    private val repository: GithubReposRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchActivityViewModel(
            repository
        ) as T
    }

}