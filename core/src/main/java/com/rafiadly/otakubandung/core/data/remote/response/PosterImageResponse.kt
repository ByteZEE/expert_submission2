package com.rafiadly.otakubandung.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class PosterImageResponse(
    @SerializedName("medium")
    val medium: String?,

    @SerializedName("large")
    val large: String?,

    @SerializedName("original")
    val original: String?,
)
