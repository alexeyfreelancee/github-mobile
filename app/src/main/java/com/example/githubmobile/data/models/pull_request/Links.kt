package com.example.githubmobile.data.models.pull_request

import com.google.gson.annotations.SerializedName

data class Links(@SerializedName("comments")
                 val comments: Comments,
                 @SerializedName("issue")
                 val issue: Issue,
                 @SerializedName("self")
                 val self: Self,
                 @SerializedName("review_comments")
                 val reviewComments: ReviewComments,
                 @SerializedName("commits")
                 val commits: Commits,
                 @SerializedName("statuses")
                 val statuses: Statuses,
                 @SerializedName("html")
                 val html: Html,
                 @SerializedName("review_comment")
                 val reviewComment: ReviewComment)