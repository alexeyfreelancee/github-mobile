package com.example.githubmobile.data.models.issue

import com.google.gson.annotations.SerializedName

data class LabelsItem(@SerializedName("default")
                      val default: Boolean = false,
                      @SerializedName("color")
                      val color: String = "",
                      @SerializedName("name")
                      val name: String = "",
                      @SerializedName("description")
                      val description: String = "",
                      @SerializedName("id")
                      val id: Int = 0,
                      @SerializedName("url")
                      val url: String = "",
                      @SerializedName("node_id")
                      val nodeId: String = "")