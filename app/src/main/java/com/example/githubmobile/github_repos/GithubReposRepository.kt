package com.example.githubmobile.github_repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubmobile.SharedPrefsProvider
import com.example.githubmobile.models.github_repository.GithubRepository
import com.example.githubmobile.networking.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GithubReposRepository(
    private val retrofitClient: RetrofitClient,
    private val sharedPrefsProvider: SharedPrefsProvider
) {
    private val header = mapOf("Authorization" to "token ${sharedPrefsProvider.loadAccessToken()}")


    fun getReposForUser(): LiveData<ArrayList<GithubRepository>> {
        val username = sharedPrefsProvider.loadUsername()
        return object : LiveData<ArrayList<GithubRepository>>() {
            override fun onActive() {
                super.onActive()
                CoroutineScope(IO).launch {
                    username?.let {
                        val response = retrofitClient.githubApi.getReposForUser(header, it)
                        withContext(Main) {
                            value = response
                        }
                    }

                }


            }
        }
    }


    suspend fun getReposByName(name: String): LiveData<ArrayList<GithubRepository>> {
        val result = MutableLiveData<ArrayList<GithubRepository>>()

        val response = retrofitClient.githubApi.getReposByName(header, name).items
        val arrayList = ArrayList<GithubRepository>()
        response.forEach {
            arrayList.add(it)
        }

        result.value = arrayList



        return result
    }


}
