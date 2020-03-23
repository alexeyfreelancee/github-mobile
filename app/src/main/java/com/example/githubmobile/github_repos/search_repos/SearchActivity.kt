package com.example.githubmobile.github_repos.search_repos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.R
import com.example.githubmobile.databinding.ActivitySearchBinding
import com.example.githubmobile.github_repos.GithubReposListAdapter
import com.example.githubmobile.utils.gone
import kotlinx.android.synthetic.main.activity_search.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SearchActivity : AppCompatActivity(), KodeinAware {
    private var rv_adapter: GithubReposListAdapter = GithubReposListAdapter()
    private lateinit var viewModel: SearchActivityViewModel
    override val kodein by kodein()
    private val factory: SearchActivityViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binder: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel =
            ViewModelProviders.of(this, factory).get(SearchActivityViewModel::class.java)
        binder.viewmodel = viewModel

        initRecyclerView()
        initObservers()

    }

    private fun initObservers() {
        viewModel.repos.observe(this, Observer {
            rv_adapter.createList(it)
        })
    }


    private fun initRecyclerView() {
        rv_search_repos.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = rv_adapter
        }
    }


}
