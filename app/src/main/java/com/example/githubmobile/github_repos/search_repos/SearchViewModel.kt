package com.example.githubmobile.github_repos.search_repos

import android.view.View
import androidx.lifecycle.*
import com.example.githubmobile.data.GitRepository
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.github_repository.GithubRepo
import kotlinx.coroutines.launch

class SearchActivityViewModel(private val repository: GitRepositoryInterface) : ViewModel() {
    private var _repos = MutableLiveData<ArrayList<GithubRepo>>()
    var repos: LiveData<ArrayList<GithubRepo>> = _repos

    var name = MutableLiveData<String>()

    private var _isUpdating = MutableLiveData<Boolean>(false)
    var isUpdating: LiveData<Boolean> = _isUpdating

    var reposEmpty: LiveData<Boolean> = repos.map { it.isNullOrEmpty() }

    fun getReposByName(view: View?) = viewModelScope.launch {
        name.value?.let { name ->
            _isUpdating.value = true
            _repos.value = repository.getReposByName(name)
            _isUpdating.value = false
        }
    }


}




@Suppress("UNCHECKED_CAST")
class SearchActivityViewModelFactory(
    private val repository: GitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchActivityViewModel(
            repository
        ) as T
    }

}