package com.example.githubmobile.user_profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubmobile.data.models.User
import com.example.githubmobile.databinding.ProfileFragmentBinding
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {
    private val rv_adapter = UserEventsAdapter()
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding = ProfileFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        initRecyclerView()
        initObservers()
    }


    private fun initObservers() {
        viewModel.user.observe(this, Observer { user ->
            initUi(user)
        })

        viewModel.events.observe(this, Observer {
            rv_adapter.createList(it)
        })
    }


    private fun initRecyclerView() {
        rv_user_activity.apply {
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = rv_adapter
        }
    }


    private fun initUi(user: User) {
        Glide.with(requireActivity().applicationContext)
            .load(user.avatar_url)
            .into(user_image)
        user_username.text = user.login
        user_country.text = user.location
        user_joined_at.text = user.created_at
        user_followers.text = user.followers.toString()
        user_following.text = user.following.toString()
        user_repositories.text = user.public_repos.toString()
        card_user.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(user.html_url)
            startActivity(intent)
        }


    }
}

