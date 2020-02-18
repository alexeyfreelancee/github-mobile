package com.example.githubmobile.placeholder_activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.githubmobile.R
import com.example.githubmobile.authorization.MainActivity
import com.example.githubmobile.github_repos.GithubReposFragment
import com.example.githubmobile.profile.ProfileFragment
import com.example.githubmobile.search.SearchFragment
import com.example.githubmobile.showToast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_place_holder.*
import kotlinx.android.synthetic.main.nav_header.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PlaceHolderActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, KodeinAware {
    override val kodein by kodein()
    private val factory: PlaceHolderViewModelFactory by instance()
    private lateinit var placeHolderViewModel: PlaceHolderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_holder)
        setSupportActionBar(toolbar)

        initNavigationDrawer()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment())
                .commit()
            nav_view.setCheckedItem(R.id.nav_user)
        }

        placeHolderViewModel = ViewModelProvider(this, factory).get(PlaceHolderViewModel::class.java)
        placeHolderViewModel.updateUserInfo()
        placeHolderViewModel.user.observe(this, Observer{
            with(nav_view.getHeaderView(0)){
                header_register_date.text = it.created_at
                header_username.text = it.login
                Glide.with(this@PlaceHolderActivity)
                    .load(it.avatar_url)
                    .into(header_icon)

            }
        })


    }


    private fun initNavigationDrawer() {
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.open,
            R.string.close
        )

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.getHeaderView(0).header_username.text = "aue"
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_user -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment())
                    .commit()
            }
            R.id.nav_repos -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, GithubReposFragment())
                    .commit()
            }
            R.id.nav_logout -> {
                showToast("Logged out")
                startActivity(Intent(this@PlaceHolderActivity, MainActivity::class.java))
                finish()
            }
            R.id.nav_search -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SearchFragment())
                    .commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
