package com.example.githubmobile.authorization

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.placeholder_activity.PlaceHolderActivity
import com.example.githubmobile.R
import com.example.githubmobile.models.AccessToken
import com.example.githubmobile.showToast
import kotlinx.android.synthetic.main.activity_main.*

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), AuthorizationListener, KodeinAware {
    private val clientId = "Iv1.6d9b7fd23d2a7b19"
    private val redirectUri = "github://callback"

    private lateinit var authorizationViewModel: AuthorizationViewModel
    override val kodein by kodein()
    private val factory : AuthorizationViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authorizationViewModel = ViewModelProvider(this, factory).get(AuthorizationViewModel::class.java)
        authorizationViewModel.authorizationListener = this

        b_authorize.setOnClickListener {
            authorize()
        }
    }

    private fun authorize() {
        intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/login/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUri")
        )
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val code = intent.data?.getQueryParameter("code")
        code?.let {
            authorizationViewModel.callbackHandled(code)
        }
    }

    override fun successAuthorization(accessToken: LiveData<AccessToken>) {
        showToast("Success authorization")
        accessToken.observe(this, Observer {
            intent = Intent(this@MainActivity, PlaceHolderActivity::class.java)
            intent.putExtra("access_token", it.accessToken)
            startActivity(intent)
            finish()
        })
    }




}
