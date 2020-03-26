package com.example.githubmobile.data.models.repos_list

import com.example.githubmobile.data.models.github_repository.GithubRepo
import com.google.gson.annotations.SerializedName

data class ReposList(
    @SerializedName("total_count") val total_count : Int,
    @SerializedName("incomplete_results") val incomplete_results : Boolean,
    @SerializedName("items") val items : ArrayList<GithubRepo>
)