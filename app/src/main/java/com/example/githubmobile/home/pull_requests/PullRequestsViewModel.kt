package com.example.githubmobile.home.pull_requests

import androidx.lifecycle.*
import com.example.githubmobile.data.models.pull_request.PullRequest
import com.example.githubmobile.data.repository.GitRepository
import kotlinx.coroutines.launch

class PullRequestsViewModel(private val repository: GitRepository) : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _pullRequests = MutableLiveData<ArrayList<PullRequest>>()
    val pullRequests: LiveData<ArrayList<PullRequest>> = _pullRequests

    init {
        loadPullRequests()
    }

    fun loadPullRequests() {
        viewModelScope.launch {
            _dataLoading.value = true
            _pullRequests.value = repository.getPullRequests()
            _dataLoading.value = false
        }

    }
}

@Suppress("UNCHECKED_CAST")
class PullRequestsViewModelFactory(
    private val repository: GitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PullRequestsViewModel(repository) as T
    }

}
