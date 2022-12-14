package com.yaritzama.marvelapp.data.models.comicsresponse


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("path")
    val path: String?
)