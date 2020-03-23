package com.example.githubmobile.user_profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubmobile.data.source.FakeTestRepository
import com.example.githubmobile.github_repos.search_repos.SearchActivityViewModel
import com.example.githubmobile.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserViewModelTest{
    lateinit var viewModel: UserViewModel
    private var testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        val repository =
            FakeTestRepository()
        viewModel = UserViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun updateUserInfo_user_Updates(){
        viewModel.updateUserInfo()
        val user = viewModel.user.getOrAwaitValue()
        assertEquals(user.html_url, "html_url")
    }


    @Test
    fun updateUserEvents_events_Updates(){
        viewModel.updateUserEvents()
        val events = viewModel.events.getOrAwaitValue()
        assertEquals(events[0].repo.name, "name")
    }
}