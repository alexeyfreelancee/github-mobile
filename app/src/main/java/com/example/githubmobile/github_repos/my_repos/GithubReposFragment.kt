package com.example.githubmobile.github_repos.my_repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.R
import com.example.githubmobile.github_repos.GithubReposListAdapter
import kotlinx.android.synthetic.main.github_repos_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GithubReposFragment : Fragment(), KodeinAware{
    private lateinit var githubReposListAdapter: GithubReposListAdapter

    override val kodein by kodein()
    private val factory: GithubReposViewModelFactory by instance()
    private lateinit var viewModel: GithubReposViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, factory).get(GithubReposViewModel::class.java)
        return inflater.inflate(R.layout.github_repos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
            githubReposListAdapter = GithubReposListAdapter()
            adapter = githubReposListAdapter
        }
    }

}
