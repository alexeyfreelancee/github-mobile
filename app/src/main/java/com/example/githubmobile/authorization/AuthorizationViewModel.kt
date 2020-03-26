package com.example.githubmobile.authorization

import android.view.View
import androidx.lifecycle.*
import com.example.githubmobile.data.repository.GitRepository
import com.example.githubmobile.utils.Event
import com.example.githubmobile.utils.log
import kotlinx.coroutines.launch

class AuthorizationViewModel(private val repository: GitRepository) : ViewModel() {
    var username = MutableLiveData<String>()
    var password  = MutableLiveData<String>()

    private val _authEvent = MutableLiveData<Event<Boolean>>()
    val authEvent : LiveData<Event<Boolean>> = _authEvent

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading : LiveData<Boolean> = _dataLoading

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast : LiveData<Event<String>> = _showToast

    init {
        loadAccessKey()
    }

    fun authorize(view: View){
        if (checkEmptyText()){
            viewModelScope.launch {
                _dataLoading.postValue(true)
                _authEvent.postValue(Event(repository.authorize(username.value!!, password.value!!)))
                _dataLoading.postValue(false)
            }
        } else {
            _showToast.postValue(Event("Enter username and password"))
        }


    }

    private fun checkEmptyText() : Boolean{
        return !(username.value.isNullOrBlank() || password.value.isNullOrBlank())
    }

    private fun loadAccessKey(){
       val key = repository.loadSecretKey()
        key?.let {
            if(key != "null") _authEvent.postValue(Event(true))
        }
    }

}

@Suppress("UNCHECKED_CAST")
class AuthorizationViewModelFactory(
    private val repository: GitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthorizationViewModel(repository) as T
    }
}