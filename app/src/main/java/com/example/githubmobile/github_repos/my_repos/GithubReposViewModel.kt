package com.example.githubmobile.github_repos.my_repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.data.repository.GitRepository
import kotlinx.coroutines.launch

class GithubReposViewModel(var githubReposRepository: GitRepository) : ViewModel() {
    private var _reposList = MutableLiveData<ArrayList<GithubRepo>>()
    var reposList = _reposList

    init {
        getReposList()
    }

    fun getReposList() = viewModelScope.launch {
        _reposList.value = githubReposRepository.getReposForUser()
    }

}

class GithubReposViewModelFactory(
    private val repository: GitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubReposViewModel(
            repository
        ) as T
    }

}