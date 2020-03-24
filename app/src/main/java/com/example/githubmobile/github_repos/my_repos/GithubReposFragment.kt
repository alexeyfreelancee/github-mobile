package com.example.githubmobile.github_repos.my_repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.R
import com.example.githubmobile.ServiceLocator
import com.example.githubmobile.github_repos.GithubReposListAdapter
import kotlinx.android.synthetic.main.github_repos_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GithubReposFragment : Fragment() {
    private lateinit var githubReposListAdapter: GithubReposListAdapter
    private val viewModel: GithubReposViewModel by viewModels{
        GithubReposViewModelFactory(ServiceLocator.provideRepository(requireActivity().applicationContext))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.github_repos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        initObservers()

        viewModel.getReposList()
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
