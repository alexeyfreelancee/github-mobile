package com.example.githubmobile

import android.app.Application
import com.example.githubmobile.authorization.AuthorizationRepository
import com.example.githubmobile.authorization.AuthorizationViewModelFactory
import com.example.githubmobile.github_repos.GithubReposRepository
import com.example.githubmobile.github_repos.my_repos.GithubReposViewModelFactory
import com.example.githubmobile.github_repos.search_repos.SearchActivityViewModelFactory
import com.example.githubmobile.networking.NetworkConnectionInterceptor
import com.example.githubmobile.networking.RetrofitClient
import com.example.githubmobile.user_profile.UserRepository
import com.example.githubmobile.user_profile.UserViewModelFactory
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
        bind() from singleton { RetrofitClient }
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { SharedPrefsProvider(instance()) }
        bind() from singleton { AuthorizationRepository(instance(), instance())}
        bind() from singleton { AuthorizationViewModelFactory(instance()) }

        //User
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { UserViewModelFactory(instance())}

        //Repositories
        bind() from singleton { GithubReposRepository(instance(), instance()) }
        bind() from singleton { GithubReposViewModelFactory(instance())}


        //search repositories
        bind() from singleton { SearchActivityViewModelFactory(instance()) }
    }

}