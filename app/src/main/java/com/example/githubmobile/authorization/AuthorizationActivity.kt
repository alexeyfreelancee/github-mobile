package com.example.githubmobile.authorization

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.PlaceHolderActivity
import com.example.githubmobile.R
import com.example.githubmobile.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AuthorizationActivity : AppCompatActivity(), KodeinAware {
    private val clientId = "3b97901fbec977e5e3f7"
    private val redirectUri = "github://callback"

    private lateinit var viewModel: AuthorizationViewModel
    override val kodein by kodein()
    private val factory: AuthorizationViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyAvailableNetwork(this)
        viewModel = ViewModelProvider(this, factory).get(AuthorizationViewModel::class.java)
        initObservers()

        viewModel.getAccessToken()

        b_authorize.setOnClickListener {
            authorize()
        }
    }

    private fun initObservers() {
        viewModel.accessToken.observe(this, Observer { token ->
            if (token.success) {
                startPlaceHolderActivity(token.accessToken)
            } else {
                showToast("Something went wrong...")
            }
        })
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
        viewModel.onResume(
            intent.data?.getQueryParameter("code")
        )
    }


    private fun startPlaceHolderActivity(accessToken: String) {
        intent = Intent(this@AuthorizationActivity, PlaceHolderActivity::class.java)
        intent.putExtra("access_token", accessToken)
        startActivity(intent)
        finish()
    }


    private fun verifyAvailableNetwork(activity:AppCompatActivity){
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        val available = networkInfo!=null && networkInfo.isConnected
        if(!available){
            showToast("No internet connection :(" )
            finish()
        }
    }
}
