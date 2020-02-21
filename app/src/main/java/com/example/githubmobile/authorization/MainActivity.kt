package com.example.githubmobile.authorization

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.R
import com.example.githubmobile.models.AccessToken
import com.example.githubmobile.showToast
import com.example.githubmobile.PlaceHolderActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), AuthorizationListener, KodeinAware {
    private val clientId = "3b97901fbec977e5e3f7"
    private val redirectUri = "github://callback"

    private lateinit var authorizationViewModel: AuthorizationViewModel

    override val kodein by kodein()
    private val factory: AuthorizationViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authorizationViewModel =
            ViewModelProvider(this, factory).get(AuthorizationViewModel::class.java)
        authorizationViewModel.authorizationListener = this
        authorizationViewModel.getAccessToken()
        authorizationViewModel.accessToken.observe(this, Observer {
            it?.let {
                startPlaceHolderActivity(it)
            }
        })

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
        accessToken.observe(this, Observer {
            if (it.success) {
                startPlaceHolderActivity(it.accessToken)
            } else {
                showToast("Something went wrong...")
            }

        })
    }


    private fun startPlaceHolderActivity(accessToken: String) {
        intent = Intent(this@MainActivity, PlaceHolderActivity::class.java)
        intent.putExtra("access_token", accessToken)
        startActivity(intent)
        finish()
    }


}
