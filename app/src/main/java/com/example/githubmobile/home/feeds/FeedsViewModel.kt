package com.example.githubmobile.home.feeds

import androidx.lifecycle.*
import com.example.githubmobile.data.repository.GitRepository
import com.sun.syndication.feed.synd.SyndEntry

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedsViewModel(private val repository: GitRepository) : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _feeds = MutableLiveData<ArrayList<SyndEntry>>()
    val feeds: LiveData<ArrayList<SyndEntry>> = _feeds

    init {

        loadFeeds()
    }

    fun loadFeeds() {
        CoroutineScope(Dispatchers.IO).launch {
            _dataLoading.postValue(true)
            _feeds.postValue( repository.getFeeds())
            _dataLoading.postValue(false)
        }

    }
}

@Suppress("UNCHECKED_CAST")
class FeedsViewModelFactory(
    private val repository: GitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedsViewModel(repository) as T
    }

}
