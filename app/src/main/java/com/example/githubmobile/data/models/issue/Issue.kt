package com.example.githubmobile.data.models.issue

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("pull_request")
    val pullRequest: PullRequest?,
    @SerializedName("comments")
    val comments: Int = 0,
    @SerializedName("assignees")
    val assignees: List<AssigneesItem>?,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("body")
    val body: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("labels")
    val labels: List<LabelsItem>?,
    @SerializedName("labels_url")
    val labelsUrl: String = "",
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("milestone")
    val milestone: Milestone,
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("events_url")
    val eventsUrl: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("comments_url")
    val commentsUrl: String = "",
    @SerializedName("active_lock_reason")
    val activeLockReason: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("repository_url")
    val repositoryUrl: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("assignee")
    val assignee: Assignee,
    @SerializedName("locked")
    val locked: Boolean = false,
    @SerializedName("user")
    val user: User,
    @SerializedName("node_id")
    val nodeId: String = ""

)

