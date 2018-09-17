package br.com.igym.data.models

import com.google.gson.annotations.SerializedName

class ApiPost(
        @SerializedName("userId") val userId: Int?,
        @SerializedName("id") val id: Int?,
        @SerializedName("title") val title: String?,
        @SerializedName("body") val body: String?
)