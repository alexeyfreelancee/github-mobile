package com.example.githubmobile.github_repos.my_repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubmobile.github_repos.GithubReposRepository
import com.example.githubmobile.models.github_repository.GithubRepository

class GithubReposViewModel(var githubReposRepository: GithubReposRepository) : ViewModel() {
    var reposList: LiveData<ArrayList<GithubRepository>>? = null


    fun getReposList(){
      reposList = githubReposRepository.getReposForUser()
    }
}
