package com.example.githubmobile.data.models.github_repository

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("description") val description : String,
    @SerializedName("owner") val owner : Owner,
    @SerializedName("language") val language : String,
    @SerializedName("stargazers_count") val stargazers_count : Int,
    @SerializedName("name") val name : String,
    @SerializedName("html_url") val html_url : String
)