package com.example.githubmobile.data.models.feeds

import com.google.gson.annotations.SerializedName

data class FeedsLink(@SerializedName("security_advisories_url")
                     val securityAdvisoriesUrl: String = "",
                     @SerializedName("_links")
                     val Links: Links,
                     @SerializedName("current_user_url")
                     val currentUserUrl: String = "",
                     @SerializedName("current_user_organization_url")
                     val currentUserOrganizationUrl: String = "",
                     @SerializedName("user_url")
                     val userUrl: String = "",
                     @SerializedName("current_user_actor_url")
                     val currentUserActorUrl: String = "",
                     @SerializedName("current_user_public_url")
                     val currentUserPublicUrl: String = "",
                     @SerializedName("timeline_url")
                     val timelineUrl: String = "")