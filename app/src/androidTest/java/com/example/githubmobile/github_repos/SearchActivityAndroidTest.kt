package com.example.githubmobile.github_repos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.githubmobile.R
import com.example.githubmobile.ServiceLocator
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.source.FakeAndroidTestRepository
import com.example.githubmobile.github_repos.search_repos.SearchActivity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
class SearchActivityAndroidTest {
    lateinit var mRepo: GitRepositoryInterface

    @Before
    fun setup() {
        mRepo = FakeAndroidTestRepository()
        ServiceLocator.repository = mRepo
    }

    @After
    fun tearDown() {
        ServiceLocator.reset()
    }

    @get:Rule
    var activityRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun whenListEmpty_emptyText_Visible() = runBlocking {
        mRepo.getEmptyRepos()

        onView(withId(R.id.tv_empty_search)).check(matches(isDisplayed()))
        return@runBlocking
    }

    @Test
    fun whenListNotEmpty_emptyText_Gone() = runBlocking {
        mRepo.getReposByName("name")
        onView(withId(R.id.tv_empty_search)).check(matches(not(isDisplayed())))
        return@runBlocking
    }
}
