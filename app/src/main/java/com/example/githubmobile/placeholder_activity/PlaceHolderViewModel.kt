package com.example.githubmobile.placeholder_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubmobile.models.User

class PlaceHolderViewModel(private val repository: PlaceHolderRepository) : ViewModel() {
    lateinit var user: LiveData<User>

    fun updateUserInfo() {
        user = repository.getUserInfo()
    }

}