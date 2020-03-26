package com.example.githubmobile.home.pull_requests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.githubmobile.R
import com.example.githubmobile.databinding.PullRequestsFragmentBinding
import kotlinx.android.synthetic.main.pull_requests_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class PullRequestsFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory : PullRequestsViewModelFactory by instance()
    private lateinit var viewModel: PullRequestsViewModel
    private lateinit var binding: PullRequestsFragmentBinding
    private val pullRequestsAdapter = PullRequestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(PullRequestsViewModel::class.java)
        binding = PullRequestsFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        initObservers()
    }

    private fun initObservers(){
        viewModel.pullRequests.observe(viewLifecycleOwner, Observer {
            pullRequestsAdapter.createNewList(it)
        })
    }
    private fun initRecyclerView(){
        pull_requests_list.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            adapter = pullRequestsAdapter
        }
    }
}
