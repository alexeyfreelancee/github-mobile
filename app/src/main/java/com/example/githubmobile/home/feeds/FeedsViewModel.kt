package com.example.githubmobile.home.feeds

import androidx.lifecycle.*
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.feed.Feed
import com.example.githubmobile.user_profile.ProfileViewModel
import kotlinx.coroutines.launch

class FeedsViewModel(private val repository: GitRepositoryInterface) : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _feeds = MutableLiveData<ArrayList<Feed>>()
    val feeds: LiveData<ArrayList<Feed>> = _feeds

    init {

        loadFeeds()
    }

    fun loadFeeds() {
        viewModelScope.launch{
            _dataLoading.value = true
            //    _feeds.value = repository.getFeeds()
            _dataLoading.value = false
        }

    }
}

@Suppress("UNCHECKED_CAST")
class FeedsViewModelFactory(
    private val repository: GitRepositoryInterface
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedsViewModel(repository) as T
    }

}
