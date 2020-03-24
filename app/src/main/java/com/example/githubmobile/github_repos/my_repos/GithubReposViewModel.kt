package com.example.githubmobile.github_repos.my_repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.githubmobile.data.GitRepository
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.github_repository.GithubRepo
import kotlinx.coroutines.launch

class GithubReposViewModel(var githubReposRepository: GitRepositoryInterface) : ViewModel() {
    private var _reposList = MutableLiveData<ArrayList<GithubRepo>>()
    var reposList = _reposList


    fun getReposList() = viewModelScope.launch {
        _reposList.value = githubReposRepository.getReposForUser()
    }

}

class GithubReposViewModelFactory(
    private val repository: GitRepositoryInterface
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubReposViewModel(
            repository
        ) as T
    }

}