package com.example.githubmobile.github_repos.my_repos

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.R
import com.example.githubmobile.github_repos.GithubReposListAdapter
import kotlinx.android.synthetic.main.activity_git_repos.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GithubReposActivity : AppCompatActivity(), KodeinAware{
    private val githubReposListAdapter = GithubReposListAdapter()

    override val kodein by kodein()
    private val factory: GithubReposViewModelFactory by instance()
    private lateinit var viewModel: GithubReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_repos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Repositories"

        viewModel = ViewModelProvider(this, factory).get(GithubReposViewModel::class.java)
        initRecyclerView()
        initObservers()
    }


    private fun initObservers(){
        viewModel.reposList.observe(this, Observer {
            githubReposListAdapter.createList(it)
        })
    }


    private fun initRecyclerView(){
        rv_repos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter =  githubReposListAdapter
        }
    }

}
