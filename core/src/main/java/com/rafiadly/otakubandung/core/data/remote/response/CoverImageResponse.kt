package com.rafiadly.otakubandung.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CoverImageResponse(
    @SerializedName("small")
    val small: String?,

    @SerializedName("large")
    val large: String?,

    @SerializedName("original")
    val original: String?,
)
