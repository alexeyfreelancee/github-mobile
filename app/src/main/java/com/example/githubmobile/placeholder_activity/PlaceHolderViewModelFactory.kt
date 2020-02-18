package com.example.githubmobile.placeholder_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubmobile.authorization.AuthorizationRepository
import com.example.githubmobile.authorization.AuthorizationViewModel

class PlaceHolderViewModelFactory(
    private val repository: PlaceHolderRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaceHolderViewModel(repository) as T
    }

}