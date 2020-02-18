package com.example.githubmobile.github_repos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.githubmobile.R

class GithubReposFragment : Fragment() {

    companion object {
        fun newInstance() = GithubReposFragment()
    }

    private lateinit var viewModel: GithubReposViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.github_repost_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GithubReposViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
