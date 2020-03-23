package com.example.githubmobile.data.models.events

import com.google.gson.annotations.SerializedName

data class Repo (
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)