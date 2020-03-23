package com.example.githubmobile.authorization

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubmobile.data.source.FakeTestRepository
import com.example.githubmobile.user_profile.UserViewModel
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

class AuthorizationViewModelTest{
    lateinit var viewModel: AuthorizationViewModel
    private var testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        val repository =
            FakeTestRepository()
        viewModel = AuthorizationViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun getAccessToken_accessToken_Updates(){
        viewModel.getAccessToken()
        val token = viewModel.accessToken.getOrAwaitValue()
        assertEquals(token.accessToken, "accessToken")
    }

    @Test
    fun getAccessTokenByCode_accessToken_Updates(){
        viewModel.getAccessTokenByCode("code")
        val token = viewModel.accessToken.getOrAwaitValue()
        assertEquals(token.accessToken, "accessToken")
    }
}