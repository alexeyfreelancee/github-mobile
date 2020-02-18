package com.example.githubmobile.authorization

import androidx.lifecycle.LiveData
import com.example.githubmobile.models.AccessToken

interface AuthorizationListener {
    fun successAuthorization(accessToken: LiveData<AccessToken>)
}