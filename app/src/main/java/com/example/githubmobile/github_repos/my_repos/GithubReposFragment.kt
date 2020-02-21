package com.example.githubmobile.github_repos.my_repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.R
import com.example.githubmobile.github_repos.GithubReposListAdapter
import kotlinx.android.synthetic.main.github_repost_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GithubReposFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: GithubReposViewModelFactory by instance()
    private lateinit var githubReposListAdapter: GithubReposListAdapter
    private lateinit var viewModel: GithubReposViewModel

    companion object {
        fun newInstance() =
            GithubReposFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.github_repost_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubReposViewModel::class.java)
        viewModel.getReposList()
        viewModel.reposList?.observe(this, Observer {
            githubReposListAdapter.createList(it)
        })
    }


    private fun initRecyclerView(){
        rv_repos.apply {
            layoutManager = LinearLayoutManager(context)
            githubReposListAdapter =
                GithubReposListAdapter()
            adapter = githubReposListAdapter
        }
    }

}
