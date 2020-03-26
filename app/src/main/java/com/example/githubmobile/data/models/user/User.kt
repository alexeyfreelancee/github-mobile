package com.example.githubmobile.data.models.user

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("login") val login : String,
                @SerializedName("avatar_url") val avatar_url : String,
                @SerializedName("html_url") val html_url : String,
                @SerializedName("location") val location : String,
                @SerializedName("created_at") var created_at : String,
                @SerializedName("followers") val followers : Int,
                @SerializedName("following") val following : Int,
                @SerializedName("public_repos") val public_repos : Int)

