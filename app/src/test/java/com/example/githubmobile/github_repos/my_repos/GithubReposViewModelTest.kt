package com.example.githubmobile.github_repos.my_repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubmobile.data.source.FakeTestRepository
import com.example.githubmobile.utils.getOrAwaitValue
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GithubReposViewModelTest {
    lateinit var viewModel: GithubReposViewModel
    private var testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val repository =
            FakeTestRepository()
        viewModel = GithubReposViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getRepos_repos_notEmpty(){
        viewModel.getReposList()
        val result = viewModel.reposList.getOrAwaitValue()
        assertTrue(result.size > 0)
    }
}