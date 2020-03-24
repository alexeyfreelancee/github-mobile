package com.example.githubmobile.user_profile

import androidx.lifecycle.*
import com.example.githubmobile.data.GitRepository
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.User
import com.example.githubmobile.data.models.events.UserEvent
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class UserViewModel(private val repository: GitRepositoryInterface) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _events = MutableLiveData<ArrayList<UserEvent>>()
    var events: LiveData<ArrayList<UserEvent>> = _events

    fun updateUserInfo() = viewModelScope.launch {
        val userData = repository.getUserInfo()
        userData.apply {
            created_at = "Joined at ${getDate(created_at)}"
        }
        _user.value = userData

    }

    fun updateUserEvents() = viewModelScope.launch {
        _events.value = repository.getUserEvents()
        _events.value?.forEach { event -> event.created_at = getDate(event.created_at) }
    }

    private fun getDate(string: String): String {
        val dateString = string.replace("[TZ]".toRegex(), " ")
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).parse(dateString)
        val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        date?.let {
            return simpleDateFormat.format(date)
        }
        return "no date"
    }

}

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(
    private val repository: GitRepositoryInterface
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }

}