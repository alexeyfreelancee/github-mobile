package com.example.githubmobile.github_repos.search_repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.githubmobile.data.source.FakeTestRepository
import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.example.githubmobile.utils.getOrAwaitValue
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchActivityViewModelTest{
    lateinit var viewModel: SearchActivityViewModel
    private var testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        val repository =
            FakeTestRepository()
        viewModel = SearchActivityViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getReposByName_repos_notNull(){
        viewModel.name = "name"
        viewModel.getReposByName(null)
        val repos = viewModel.repos.getOrAwaitValue()
        assertTrue(repos.size > 0)

    }


    @Test
    fun getReposByName_null_NotCrash(){
        viewModel.name = null
        viewModel.getReposByName(null)
    }



}