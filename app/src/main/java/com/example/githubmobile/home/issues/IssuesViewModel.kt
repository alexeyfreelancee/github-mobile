package com.example.githubmobile.home.issues

import androidx.lifecycle.*
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.issue.Issue

import kotlinx.coroutines.launch

class IssuesViewModel(private val repository: GitRepositoryInterface) : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _issues = MutableLiveData<ArrayList<Issue>>()
    val issues: LiveData<ArrayList<Issue>> = _issues

    init {

        loadIssues()
    }

    fun loadIssues(){
        viewModelScope.launch {
            _dataLoading.value = true
           _issues.value = repository.getIssues()
            _dataLoading.value = false
        }

    }
}
@Suppress("UNCHECKED_CAST")
class IssuesViewModelFactory(
    private val repository: GitRepositoryInterface
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IssuesViewModel(repository) as T
    }

}