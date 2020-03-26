package com.example.githubmobile.data.models.feeds

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("href")
                val href: String = "",
                @SerializedName("type")
                val type: String = "")