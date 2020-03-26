package com.example.githubmobile.user_profile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubmobile.R
import com.example.githubmobile.data.models.user.User
import com.example.githubmobile.databinding.ActivityProfileBinding
import kotlinx.android.synthetic.main.activity_profile.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ProfileActivity : AppCompatActivity(), KodeinAware {

    private val rv_adapter = UserEventsAdapter()
    override val kodein by kodein()
    private val factory: UserViewModelFactory by instance()

    private lateinit var viewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"

        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        DataBindingUtil.setContentView<ActivityProfileBinding>(this, R.layout.activity_profile).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileActivity
        }
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
        Glide.with(applicationContext)
            .load(user.avatar_url)
            .into(user_image)
        card_user.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(user.html_url)
            startActivity(intent)
        }


    }


}
