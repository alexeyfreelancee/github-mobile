package com.example.githubmobile.data.models.pull_request

import com.google.gson.annotations.SerializedName

data class Head(@SerializedName("ref")
                val ref: String = "",
                @SerializedName("repo")
                val repo: Repo,
                @SerializedName("label")
                val label: String = "",
                @SerializedName("sha")
                val sha: String = "",
                @SerializedName("user")
                val user: User)