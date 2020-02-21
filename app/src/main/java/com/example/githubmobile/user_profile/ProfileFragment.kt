package com.example.githubmobile.user_profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubmobile.R
import com.example.githubmobile.makeInvisible
import com.example.githubmobile.makeVisible
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {
    private val rv_adapter = UserEventsAdapter()
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()

    companion object {
        fun newInstance() =
            ProfileFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()

        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)

        observeUserInfo()
        observeUserEvents()
    }

    private fun observeUserEvents(){
        viewModel.updateUserEvents()
        viewModel.events.observe(this, Observer {
            if(it.size > 0){
                user_empty_activity.makeInvisible()
                rv_user_activity.makeVisible()
                rv_adapter.createList(it)
            } else{
               user_empty_activity.makeInvisible()
                rv_user_activity.makeInvisible()
            }

        })
    }

    private fun observeUserInfo(){
        viewModel.updateUserInfo()
        viewModel.user.observe(this, Observer {
            val user = it
            Glide.with(context!!)
                .load(user.avatar_url)
                .into(user_image)
            user_username.text = user.login
            user_country.text = user.location
            user_joined_at.text = user.created_at
            user_followers.text = user.followers.toString()
            user_following.text = user.following.toString()
            user_repositories.text = user.public_repos.toString()
            card_user.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(user.html_url)
                startActivity(intent)
            }
        })
    }



    private fun initRecyclerView(){
        rv_user_activity.apply {

            layoutManager = object: LinearLayoutManager(context){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = rv_adapter
        }
    }

}
