package com.example.githubmobile.authorization

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.R
import com.example.githubmobile.utils.showToast
import com.example.githubmobile.PlaceHolderActivity
import com.example.githubmobile.ServiceLocator
import com.example.githubmobile.utils.SharedPrefsProvider
import com.example.githubmobile.utils.log
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AuthorizationActivity : AppCompatActivity() {
    private val clientId = "3b97901fbec977e5e3f7"
    private val redirectUri = "github://callback"

    private val viewModel: AuthorizationViewModel by viewModels<AuthorizationViewModel>{
        AuthorizationViewModelFactory(ServiceLocator.provideRepository(applicationContext))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
        viewModel.getAccessToken()

        b_authorize.setOnClickListener {
            authorize()
        }
    }

    private fun initObservers(){
        viewModel.accessToken.observe(this, Observer {token ->
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


}
