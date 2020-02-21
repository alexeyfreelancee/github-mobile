package com.example.githubmobile.github_repos.search_repos

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubmobile.github_repos.GithubReposRepository
import com.example.githubmobile.models.github_repository.GithubRepository
import kotlinx.coroutines.launch

class SearchActivityViewModel(private val repository: GithubReposRepository) : ViewModel() {
   lateinit var reposList: LiveData<ArrayList<GithubRepository>>
    var name: String = ""
    var listener: SearchListener? = null

    fun getReposByName(view: View) {
        listener?.searchStarted()
        viewModelScope.launch {
            reposList = repository.getReposByName(name)
            listener?.searchCompleted(reposList)
        }

    }


}