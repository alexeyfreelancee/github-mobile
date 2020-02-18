package com.example.githubmobile.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class AuthorizationViewModelFactory (
    private val repository: AuthorizationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthorizationViewModel(repository) as T
    }
}