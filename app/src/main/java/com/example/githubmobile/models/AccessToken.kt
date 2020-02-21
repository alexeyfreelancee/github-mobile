package com.example.githubmobile.models

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token") val accessToken: String = "null",
    @SerializedName("token_type") val tokenType: String = "null",
    val success: Boolean = true
)