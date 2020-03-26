package com.example.githubmobile

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.githubmobile.authorization.AuthorizationActivity
import com.example.githubmobile.github_repos.my_repos.GithubReposActivity
import com.example.githubmobile.github_repos.search_repos.SearchActivity
import com.example.githubmobile.home.feeds.FeedsFragment
import com.example.githubmobile.home.issues.IssuesFragment
import com.example.githubmobile.home.pull_requests.PullRequestsFragment
import com.example.githubmobile.user_profile.ProfileActivity
import com.example.githubmobile.user_profile.ProfileViewModel
import com.example.githubmobile.user_profile.UserViewModelFactory
import com.example.githubmobile.utils.SharedPrefsProvider
import com.example.githubmobile.utils.showToast
import com.example.githubmobile.utils.visible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_place_holder.*
import kotlinx.android.synthetic.main.nav_header.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PlaceHolderActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener,
    KodeinAware {

    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()
    private lateinit var profileViewModel: ProfileViewModel
    private val prefs: SharedPrefsProvider by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_holder)
        setSupportActionBar(toolbar)

        initNavigationDrawer()
        initBottomNavigation()
        initViewModel()


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    FeedsFragment()
                )
                .commit()
            navigation.visible()
        }
    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        profileViewModel.user.observe(this, Observer {
            with(nav_view.getHeaderView(0)) {
                header_register_date.text = it.created_at
                header_username.text = it.login
                Glide.with(this@PlaceHolderActivity)
                    .load(it.avatar_url)
                    .into(header_icon)
            }

        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.issues -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        IssuesFragment()
                    )
                    .commit()
            }

            R.id.pull_requests -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        PullRequestsFragment()
                    )
                    .commit()
            }

            R.id.feeds -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        FeedsFragment()
                    )
                    .commit()
            }
            R.id.nav_home -> {
                startActivity(Intent(applicationContext, PlaceHolderActivity::class.java))
            }

            R.id.nav_user -> {
                startActivity(Intent(applicationContext, ProfileActivity::class.java))
            }

            R.id.nav_repos -> {
                startActivity(Intent(applicationContext, GithubReposActivity::class.java))
            }

            R.id.nav_search -> {
                startActivity(Intent(applicationContext, SearchActivity::class.java))
            }

            R.id.nav_logout -> {
                prefs.removeAccessToken()
                startActivity(Intent(applicationContext, AuthorizationActivity::class.java))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initBottomNavigation() {
        navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initNavigationDrawer() {
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.open,
            R.string.close
        )
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(
                this,
                R.id.fragment_container
            ),
            drawer_layout
        )
    }


}
