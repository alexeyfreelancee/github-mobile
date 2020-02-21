package com.example.githubmobile.models

import com.example.githubmobile.models.github_repository.GithubRepository
import com.google.gson.annotations.SerializedName

data class ReposList(
    @SerializedName("total_count") val total_count : Int,
    @SerializedName("incomplete_results") val incomplete_results : Boolean,
    @SerializedName("items") val items : ArrayList<GithubRepository>
)