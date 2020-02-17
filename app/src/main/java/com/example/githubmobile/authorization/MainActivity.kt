package com.example.githubmobile.authorization

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.R
import com.example.githubmobile.api.RetrofitClient
import com.example.githubmobile.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private  val clientId = "Iv1.6d9b7fd23d2a7b19"
    private val redirectUri = "github://callback"
    lateinit var authorizationViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authorizationViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        authorizationViewModel.accessToken.observe(this, Observer {
            showToast(it.accessToken)
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
        val code = intent.data?.getQueryParameter("code")
        code?.let {
            authorizationViewModel.getAccessToken(code)
        }
    }
}
