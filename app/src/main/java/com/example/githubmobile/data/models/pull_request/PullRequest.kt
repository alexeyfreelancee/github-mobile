package com.example.githubmobile.data.models.pull_request

import com.google.gson.annotations.SerializedName

data class PullRequest(@SerializedName("issue_url")
                       val issueUrl: String = "",
                       @SerializedName("_links")
                       val Links: Links,
                       @SerializedName("diff_url")
                       val diffUrl: String = "",
                       @SerializedName("created_at")
                       val createdAt: String = "",
                       @SerializedName("title")
                       val title: String = "",
                       @SerializedName("body")
                       val body: String = "",
                       @SerializedName("head")
                       val head: Head,
                       @SerializedName("author_association")
                       val authorAssociation: String = "",
                       @SerializedName("number")
                       val number: Int = 0,
                       @SerializedName("patch_url")
                       val patchUrl: String = "",
                       @SerializedName("updated_at")
                       val updatedAt: String = "",
                       @SerializedName("draft")
                       val draft: Boolean = false,
                       @SerializedName("merge_commit_sha")
                       val mergeCommitSha: String = "",
                       @SerializedName("comments_url")
                       val commentsUrl: String = "",
                       @SerializedName("review_comment_url")
                       val reviewCommentUrl: String = "",
                       @SerializedName("id")
                       val id: Int = 0,
                       @SerializedName("state")
                       val state: String = "",
                       @SerializedName("locked")
                       val locked: Boolean = false,
                       @SerializedName("commits_url")
                       val commitsUrl: String = "",
                       @SerializedName("statuses_url")
                       val statusesUrl: String = "",
                       @SerializedName("url")
                       val url: String = "",

                       @SerializedName("html_url")
                       val htmlUrl: String = "",
                       @SerializedName("review_comments_url")
                       val reviewCommentsUrl: String = "",

                       @SerializedName("user")
                       val user: User,
                       @SerializedName("node_id")
                       val nodeId: String = "",
                       @SerializedName("base")
                       val base: Base)