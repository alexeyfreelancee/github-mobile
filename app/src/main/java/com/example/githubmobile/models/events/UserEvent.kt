package com.example.githubmobile.models.events

import com.google.gson.annotations.SerializedName

data class UserEvent(
    @SerializedName("type") val type : String,
    @SerializedName("public") val public : Boolean,
    @SerializedName("repo") val repo : Repo,
    @SerializedName("created_at") var created_at : String,
    @SerializedName("id") val id : Long
)