package com.example.githubmobile.github_repos.search_repos

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.R
import com.example.githubmobile.ServiceLocator
import com.example.githubmobile.databinding.ActivitySearchBinding
import com.example.githubmobile.github_repos.GithubReposListAdapter
import com.example.githubmobile.utils.gone
import com.example.githubmobile.utils.log
import com.example.githubmobile.utils.visible
import kotlinx.android.synthetic.main.activity_search.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SearchActivity : AppCompatActivity(), SearchListener {
    private var rv_adapter: GithubReposListAdapter = GithubReposListAdapter()

    private val viewModel by viewModels<SearchActivityViewModel> {
        SearchActivityViewModelFactory(ServiceLocator.provideRepository(applicationContext))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binder: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)
        binder.viewmodel = viewModel
        viewModel.listener = this

        initRecyclerView()
        initObservers()

    }

    private fun initObservers() {
        viewModel.repos.observe(this, Observer {
            if (it.size < 1) {
                rv_search_repos.gone()
                tv_empty_search.visible()
            } else {
                rv_search_repos.visible()
                tv_empty_search.gone()
                rv_adapter.createList(it)
            }

        })
    }


    private fun initRecyclerView() {
        rv_search_repos.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = rv_adapter
        }
    }

    override fun searchStarted() {
        search_progress_bar.visible()

    }

    override fun searchCompleted() {
        search_progress_bar.gone()

    }


}
