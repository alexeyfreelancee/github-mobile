package com.example.githubmobile.home.feeds

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.renderscript.Allocation
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.githubmobile.R
import com.example.githubmobile.databinding.FeedsFragmentBinding
import kotlinx.android.synthetic.main.feeds_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FeedsFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: FeedsViewModelFactory by instance()
    private lateinit var viewModel: FeedsViewModel
    private lateinit var binding : FeedsFragmentBinding
    private val feedsListAdapter = FeedsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =  ViewModelProvider(this, factory).get(FeedsViewModel::class.java)
        binding = FeedsFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        initObservers()
    }

    private fun initObservers(){
        viewModel.feeds.observe(viewLifecycleOwner, Observer {
            feedsListAdapter.createNewList(it)
        })
    }

    private fun initRecyclerView(){
        feeds_list.apply {
            adapter = feedsListAdapter
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        }
    }

}
