package com.example.githubmobile.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubmobile.models.User
import com.example.githubmobile.models.events.UserEvent
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    lateinit var user: LiveData<User>
    var events = MutableLiveData<ArrayList<UserEvent>>()

    fun updateUserInfo() {
        user = repository.getUserInfo()
    }

    fun updateUserEvents(){
        viewModelScope.launch {
            events.value = repository.getUserEvents().value
        }
    }

}