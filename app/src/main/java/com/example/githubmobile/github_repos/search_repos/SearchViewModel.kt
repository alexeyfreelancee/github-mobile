package com.example.githubmobile.github_repos.search_repos

import android.view.View
import androidx.lifecycle.*
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.utils.log
import kotlinx.coroutines.launch

class SearchActivityViewModel(private val repository: GitRepositoryInterface) : ViewModel() {
    private var _repos = MutableLiveData<ArrayList<GithubRepo>>()
    var repos: LiveData<ArrayList<GithubRepo>> = _repos

    var name: String = ""

    private var _dataLoading = MutableLiveData<Boolean>(false)
    var dataLoading: LiveData<Boolean> = _dataLoading


    fun getReposByName(view: View?) = viewModelScope.launch {
        log(_repos.value?.size.toString())

        if (name.isNotEmpty()) {
            _dataLoading.value = true
            _repos.value = repository.getReposByName(name)
            _dataLoading.value = false
        }

    }


}


@Suppress("UNCHECKED_CAST")
class SearchActivityViewModelFactory(
    private val repository: GitRepositoryInterface
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchActivityViewModel(
            repository
        ) as T
    }

}