package com.example.githubmobile

import android.content.Context
import com.example.githubmobile.data.GitRepository
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.networking.RetrofitClient
import com.example.githubmobile.data.source.RemoteDataSource
import com.example.githubmobile.utils.SharedPrefsProvider

object ServiceLocator{
    @Volatile
    var repository: GitRepositoryInterface? = null

    @Volatile
    var remoteDataSource: RemoteDataSource? = null

    @Volatile
    var retrofitClient: RetrofitClient?= null

    @Volatile
    var sharedPrefsProvider: SharedPrefsProvider?=null

    fun reset(){
        synchronized(Any()){
            repository = null
            remoteDataSource = null
            retrofitClient = null
            sharedPrefsProvider = null
        }
    }
    fun provideRepository(context: Context) : GitRepositoryInterface{
        synchronized(this){
            return repository ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context) : GitRepositoryInterface{
        val repository = GitRepository(
           remoteDataSource ?: createRemoteDataSource(),
           sharedPrefsProvider ?: createSharedPrefsProvider(context)
        )
        this.repository = repository
        return repository
    }

    private fun createSharedPrefsProvider(context: Context) : SharedPrefsProvider{
        val sharedPrefsProvider = SharedPrefsProvider(context)
        this.sharedPrefsProvider = sharedPrefsProvider
        return sharedPrefsProvider
    }

    private fun createRemoteDataSource() : RemoteDataSource{
        val remoteDataSource = RemoteDataSource(
            retrofitClient ?: createRetrofitClient()
        )
        this.remoteDataSource = remoteDataSource
        return remoteDataSource
    }

    private fun createRetrofitClient() : RetrofitClient{
        val retrofitClient = RetrofitClient
        this.retrofitClient = retrofitClient
        return retrofitClient
    }
}