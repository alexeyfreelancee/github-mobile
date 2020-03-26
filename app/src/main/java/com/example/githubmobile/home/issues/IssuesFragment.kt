package com.example.githubmobile.home.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmobile.databinding.IssuesFragmentBinding
import kotlinx.android.synthetic.main.issues_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class IssuesFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: IssuesViewModelFactory by instance()
    private val issuesListAdapter = IssuesListAdapter()
    private lateinit var binding: IssuesFragmentBinding
    private lateinit var viewModel: IssuesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(IssuesViewModel::class.java)
        binding = IssuesFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.issues.observe(viewLifecycleOwner, Observer {
            issuesListAdapter.createNewList(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
    }

    private fun initRecyclerView() {
        issues_list.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            adapter = issuesListAdapter
        }
    }
}
