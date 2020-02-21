package com.example.githubmobile.github_repos.search_repos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.R
import com.example.githubmobile.databinding.ActivitySearchBinding
import com.example.githubmobile.github_repos.GithubReposListAdapter
import com.example.githubmobile.makeInvisible
import com.example.githubmobile.makeVisible
import com.example.githubmobile.models.github_repository.GithubRepository
import kotlinx.android.synthetic.main.activity_search.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SearchActivity : AppCompatActivity(), KodeinAware, SearchListener {
    private var rv_adapter: GithubReposListAdapter = GithubReposListAdapter()
    private lateinit var searchActivityViewModel: SearchActivityViewModel
    override val kodein by kodein()
    private val factory: SearchActivityViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binder: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)

        initRecyclerView()
        searchActivityViewModel =
            ViewModelProviders.of(this, factory).get(SearchActivityViewModel::class.java)
        searchActivityViewModel.listener = this
        binder.viewmodel = searchActivityViewModel

    }


    private fun initRecyclerView() {
        rv_search_repos.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = rv_adapter
        }
    }

    override fun searchStarted() {
        search_progress_bar.makeVisible()
        tv_empty_search.makeInvisible()
        rv_search_repos.makeInvisible()
    }

    override fun searchCompleted(reposList: LiveData<ArrayList<GithubRepository>>) {
        search_progress_bar.makeInvisible()
        reposList.observe(this, Observer {
            if(it.size < 1){
                tv_empty_search.makeVisible()
                rv_search_repos.makeInvisible()
            } else{
                rv_search_repos.makeVisible()
                tv_empty_search.makeInvisible()
                rv_adapter.createList(it)
            }

        })
    }
}
