package com.example.githubmobile.data.models.events

import com.google.gson.annotations.SerializedName

data class UserEvent(
    @SerializedName("type") val type : String,
    @SerializedName("repo") val repo : Repo,
    @SerializedName("created_at") var created_at : String
)