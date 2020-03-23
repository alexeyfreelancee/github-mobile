package com.example.githubmobile.data.models.github_repository

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login") val login : String,
    @SerializedName("avatar_url") val avatar_url : String
)