package com.example.githubmobile.authorization

import androidx.lifecycle.*
import com.example.githubmobile.data.GitRepository
import com.example.githubmobile.data.GitRepositoryInterface
import com.example.githubmobile.data.models.AccessToken
import kotlinx.coroutines.launch

class AuthorizationViewModel(private val repository: GitRepositoryInterface) : ViewModel() {
    private var _accessToken = MutableLiveData<AccessToken>()
    var accessToken: LiveData<AccessToken> = _accessToken

    fun getAccessToken() = viewModelScope.launch {
        repository.loadAccessTokenFromPrefs()?.let { token ->
            _accessToken.value = AccessToken(
                token,
                "auth",
                true
            )
        }


    }

    fun getAccessTokenByCode(code: String?) = viewModelScope.launch {
        code?.let {
            _accessToken.value = repository.getAccessByCode(code)
        }
    }

    fun onResume(code: String?) = viewModelScope.launch {
        getAccessTokenByCode(code)
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