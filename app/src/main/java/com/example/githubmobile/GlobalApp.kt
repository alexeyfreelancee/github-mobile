package com.example.githubmobile

import android.app.Application
import com.example.githubmobile.networking.AuthGithubApi
import com.example.githubmobile.networking.NetworkConnectionInterceptor
import com.example.githubmobile.authorization.AuthorizationRepository
import com.example.githubmobile.authorization.AuthorizationViewModelFactory
import com.example.githubmobile.networking.GithubApi
import com.example.githubmobile.placeholder_activity.PlaceHolderRepository
import com.example.githubmobile.placeholder_activity.PlaceHolderViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class GlobalApp : Application(), KodeinAware{

    override val kodein = Kodein.lazy{
        import(androidXModule(this@GlobalApp))

        //Authorization
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { AuthGithubApi(instance()) }
        bind() from singleton { SharedPrefsProvider(instance()) }
        bind() from singleton { AuthorizationRepository(instance(), instance())}
        bind() from singleton { AuthorizationViewModelFactory(instance()) }


        //Placeholder activity
        bind() from singleton { GithubApi(instance()) }
        bind() from singleton { PlaceHolderRepository(instance(), instance()) }
        bind() from singleton { PlaceHolderViewModelFactory(instance())}
    }


}