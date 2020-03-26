package com.example.githubmobile.user_profile

import androidx.lifecycle.*
import com.example.githubmobile.data.models.user.User
import com.example.githubmobile.data.models.events.UserEvent
import com.example.githubmobile.data.repository.GitRepository
import com.example.githubmobile.utils.parseDate
import kotlinx.coroutines.launch
import java.util.*

class ProfileViewModel(private val repository: GitRepository) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _events = MutableLiveData<ArrayList<UserEvent>>()
    var events: LiveData<ArrayList<UserEvent>> = _events


    init {
        updateUserInfo()
        updateUserEvents()

    }

    private fun updateUserInfo() = viewModelScope.launch {
        val userData = repository.getUserInfo()
        userData.apply {
            created_at = "Joined at ${created_at.parseDate()}"
        }
        _user.value = userData
    }

    private fun updateUserEvents() = viewModelScope.launch {
        _events.value = repository.getUserEvents()
        _events.value?.forEach { event -> event.created_at = event.created_at.parseDate() }
    }



}

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(
    private val repository: GitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }

}