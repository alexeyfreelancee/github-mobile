package com.example.githubmobile.data.models.issue

import com.google.gson.annotations.SerializedName

data class Milestone(@SerializedName("creator")
                     val creator: Creator,
                     @SerializedName("closed_at")
                     val closedAt: String = "",
                     @SerializedName("description")
                     val description: String = "",
                     @SerializedName("created_at")
                     val createdAt: String = "",
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("closed_issues")
                     val closedIssues: Int = 0,
                     @SerializedName("url")
                     val url: String = "",
                     @SerializedName("due_on")
                     val dueOn: String = "",
                     @SerializedName("labels_url")
                     val labelsUrl: String = "",
                     @SerializedName("number")
                     val number: Int = 0,
                     @SerializedName("updated_at")
                     val updatedAt: String = "",
                     @SerializedName("html_url")
                     val htmlUrl: String = "",
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("state")
                     val state: String = "",
                     @SerializedName("open_issues")
                     val openIssues: Int = 0,
                     @SerializedName("node_id")
                     val nodeId: String = "")