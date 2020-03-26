package com.example.githubmobile

import android.app.Application
import com.example.githubmobile.authorization.AuthorizationViewModelFactory
import com.example.githubmobile.github_repos.my_repos.GithubReposViewModelFactory
import com.example.githubmobile.github_repos.search_repos.SearchActivityViewModelFactory
import com.example.githubmobile.data.networking.NetworkConnectionInterceptor
import com.example.githubmobile.data.networking.RetrofitClient
import com.example.githubmobile.data.GitRepository
import com.example.githubmobile.data.source.RemoteDataSource
import com.example.githubmobile.home.feeds.FeedsViewModel
import com.example.githubmobile.home.feeds.FeedsViewModelFactory
import com.example.githubmobile.home.issues.IssuesViewModel
import com.example.githubmobile.home.issues.IssuesViewModelFactory
import com.example.githubmobile.home.pull_requests.PullRequestsViewModelFactory
import com.example.githubmobile.user_profile.UserViewModelFactory
import com.example.githubmobile.utils.SharedPrefsProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class GlobalApp : Application(), KodeinAware{

    override val kodein = Kodein.lazy{
        import(androidXModule(this@GlobalApp))


        bind() from singleton { RetrofitClient }
        bind() from singleton {
            NetworkConnectionInterceptor(
                instance()
            )
        }
        bind() from singleton {
            SharedPrefsProvider(
                instance()
            )
        }
        bind() from singleton { RemoteDataSource(instance()) }
        bind() from singleton {
            GitRepository(
                instance(),
                instance()
            )
        }

        bind() from singleton { PullRequestsViewModelFactory(instance()) }
        bind() from singleton { IssuesViewModelFactory(instance()) }
        bind() from singleton { FeedsViewModelFactory(instance()) }
        bind() from singleton { AuthorizationViewModelFactory(instance()) }
        bind() from singleton { UserViewModelFactory(instance())}
        bind() from singleton { GithubReposViewModelFactory(instance())}
        bind() from singleton { SearchActivityViewModelFactory(instance()) }
    }

}