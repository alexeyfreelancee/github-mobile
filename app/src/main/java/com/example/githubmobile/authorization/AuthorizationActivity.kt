package com.example.githubmobile.authorization

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.PlaceHolderActivity
import com.example.githubmobile.R
import com.example.githubmobile.databinding.ActivityMainBinding
import com.example.githubmobile.utils.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AuthorizationActivity : AppCompatActivity(), KodeinAware {
    private lateinit var viewModel: AuthorizationViewModel
    override val kodein by kodein()
    private val factory: AuthorizationViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(AuthorizationViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewmodel = viewModel
            lifecycleOwner = this@AuthorizationActivity
        }

        verifyAvailableNetwork(this)
        initObservers()
    }

    private fun initObservers() {
        viewModel.authEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let {success ->
                if(success){
                    startPlaceHolderActivity()
                } else {
                    toast("Incorrect username or password")
                }
            }
        })

        viewModel.showToast.observe(this, Observer {
            toast(it.peekContent())
            Toast.makeText(applicationContext, it.peekContent(), Toast.LENGTH_SHORT).show()
        })
    }



    private fun startPlaceHolderActivity() {
        intent = Intent(this@AuthorizationActivity, PlaceHolderActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun verifyAvailableNetwork(activity: AppCompatActivity) {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        val available = networkInfo != null && networkInfo.isConnected
        if (!available) {
            toast("No internet connection :(")
            finish()
        }
    }
}
